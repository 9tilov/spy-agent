#!/usr/bin/env python
# -*- coding: utf-8 -*-

from http.server import BaseHTTPRequestHandler,HTTPServer
from socketserver import ThreadingMixIn
import threading
import argparse
import re
import cgi
import vk
import json
import queue
import time
import requests
import sqlite3
import datetime
import traceback

# To create the server you should use: python simplewebserver.py port ip
# for example: python simplewebserver.py 8080 127.0.0.1 


class Global(object):
	request_queue = queue.Queue()
	likes_queue = queue.Queue()

def getLikeMap(i, user_id, token):
	likes_map = {}
	time_start = time.time()
	url = "https://api.vk.com/method/execute?"

	_offset = 0
	photo_count = 0
	additional_codes = []

	while _offset < photo_count or _offset == 0:
		print('current photo offset: ', _offset)

		session = vk.Session(access_token=token)
		api = vk.API(session)
		photos = api.photos.getAll(owner_id = str(i), count = 200, extended = 1, offset = _offset)
		photo_count = photos[0]
		print(photo_count)
		code = 'return ['
		for l, p in enumerate(photos[1:]):

			like_offset = 0
			like_count = 0
				
			code += 'API.likes.getList({"type": "photo", "owner_id":%s, "item_id": %s, "extended": 1, "fields": ["sex", "bdate", "photo_200"], "count": 1000, "offset": %s }),' % (str(i), p['pid'], like_offset)
			if (l % 24 == 0 and l != 0) or l == len(photos[1:]) - 1:

				print('processing  bucket: ', l)
				code += '];'
				params = dict(code=code, access_token=token)
				res = requests.post(url=url, data=params)
				time.sleep(1)
				for lk in res.json()['response']:
					if lk['count'] > 1000:
						additional_codes.append([lk['count'], p['pid']])
					for like in lk['items']:

						if 'bdate' not in like:
							like['bdate'] = ''
						if 'photo_200' not in like:
							like['photo_200'] = ''

						if like['uid'] not in likes_map:
							likes_map[like['uid']] = (1, like['last_name'], like['first_name'], like['sex'], like['bdate'], like['photo_200'])
						else:
							likes_map[like['uid']] = (likes_map[like['uid']][0] + 1, like['last_name'], like['first_name'], like['sex'], like['bdate'], like['photo_200'])
				code = 'return ['
		_offset += 200

	add_codes = []
	for add in additional_codes:
		t = add[0] / 1000
		for i in range(t):
			add_codes.append('API.likes.getList({"type": "photo", "owner_id":%s, "item_id": %s, "extended": 1, "count": 1000, "offset": %s }),' % (str(i), add[1], 1000*(i+1)))

	code = 'return ['
	for l, p in enumerate(add_codes):

		code += p
		if (l % 24 == 0 and l != 0) or l == len(add_codes) - 1:
			code += '];'
			params = dict(code=code, access_token=token)
			res = requests.post(url=url, data=params)
			time.sleep(1)
			for lk in res.json()['response']:
				if lk['count'] > 1000:
					additional_codes.append([lk['count'], p['pid']])
				for like in lk['items']:
					if 'bdate' not in like:
						like['bdate'] = ''
					if 'photo_200' not in like:
						like['photo_200'] = ''
					if like['uid'] not in likes_map:
						likes_map[like['uid']] = (1, like['last_name'], like['first_name'], like['sex'], like['bdate'], like['photo_200'])
					else:
						likes_map[like['uid']] = (likes_map[like['uid']][0] + 1, like['last_name'], like['first_name'], like['sex'], like['bdate'], like['photo_200'])

			code = 'return ['

	return likes_map
	


class HTTPRequestHandler(BaseHTTPRequestHandler):
	
	def sendMessage(self, code, message):
		self.send_response(code)
		self.end_headers()
		response = {}
		response["code"] = code
		response['message'] = message
		self.wfile.write(bytes(json.dumps(response), 'UTF-8'))	

	def waitingTurn(self, user_id, _queue):
		checkTopElement = False
		while checkTopElement == False:
			with _queue.mutex:
				topElement = _queue.queue[0]
			if topElement != user_id:
				time.sleep(1)
			else:
				checkTopElement = True	

	def doLogin(self):
		params = self.path.split('?')[-1].split('&')
		
		ID, _ = cgi.parse_header(self.headers.get('user_id'))

		token = params[0].split('=')[1] 
		print('ID: ', ID)

		conn = sqlite3.connect('test.db')
		c = conn.cursor()
		c.execute("INSERT INTO requests VALUES (?, ?, ?, ?)", (str(datetime.datetime.now()), ID, self.path, '', ))
		isTokenExist = c.execute('SELECT count(*) FROM tokens where token = ? ', (token, )).fetchone()[0]

		if isTokenExist == 0:
			c.execute("INSERT INTO tokens VALUES (?, ?, ?)", (str(datetime.datetime.now()), ID, token, ))
			
			response = {}
			response["code"] = 200
			response['message'] = 'added successfully'
			
			self.send_response(200)
			self.end_headers()
			self.wfile.write(bytes(json.dumps(response), 'UTF-8'))
		else:
			sendMessage(self, 200, 'id already exist')
		conn.commit()
		conn.close()

	def doLogout(self):
		params = self.path.split('?')[-1].split('&')
		ID, _ = cgi.parse_header(self.headers.get('user_id'))
		print('ID: ', ID)

		conn = sqlite3.connect('test.db')
		c = conn.cursor()
		c.execute("INSERT INTO requests VALUES (?, ?, ?, ?)", (str(datetime.datetime.now()), ID, self.path, '', ))
		isIdExist = c.execute('SELECT count(*) FROM tokens where user_id = ? ', (ID, )).fetchone()[0]

		if isIdExist != 0:
			c.execute("DELETE FROM tokens WHERE user_id=?", (ID,))
			response = {}
			response["code"] = 200
			response['message'] = 'logout successfully'
			self.send_response(200)
			self.end_headers()
			self.wfile.write(bytes(json.dumps(response), 'UTF-8'))
		else:
			self.sendMessage( 200, 'id doesnt exist')
		conn.commit()
		conn.close()		

	
	

	def GetInfo(self):
		user_id, _ = cgi.parse_header(self.headers.get('user_id'))
		Global.request_queue.put(user_id)
		self.waitingTurn(user_id, Global.request_queue)	

		params = self.path.split('?')[-1].split('&')
		
		print(user_id)

		conn = sqlite3.connect('test.db')
		c = conn.cursor()
		c.execute("INSERT INTO requests VALUES (?, ?, ?, ?)", (str(datetime.datetime.now()), user_id, self.path, '', ))
		isIdExist = c.execute('SELECT count(*) FROM tokens where user_id = ? ', (user_id, )).fetchone()[0]
		
		if isIdExist != 0:
			time_start = time.time()

			token = c.execute("SELECT token FROM tokens where user_id = ? order by time desc LIMIT 1", (user_id, )).fetchone()[0]
			
			session = vk.Session(access_token=token)
			api = vk.API(session)

			profiles = api.users.get(user_id = user_id, fields = ['photo_200, sex, bdate, counters'])
			friends = api.friends.get(user_id = str(37520169), count = 5000, extended = 1, offset = 0, fields = ['sex', 'second_name'])
			for f in friends:
				print(f)
			self.send_response(200)
			self.end_headers()

			response = {}

			response["first_name"] = profiles[0]['first_name']
			response["last_name"] = profiles[0]['last_name']
			response["photo_url"] = profiles[0]['photo_200']
			response["sex"] = profiles[0]['sex']
			response["bdate"] = profiles[0]['bdate']
			response['friends'] = profiles[0]['counters']['friends']
			response['subscriptions'] = profiles[0]['counters']['subscriptions']
			response['code'] = 200

			if time.time() - time_start < 1:
				time.sleep(1 - time.time() + time_start)
			
			Global.request_queue.get()
			print(response)
			self.wfile.write(bytes(json.dumps(response), 'utf-8'))
		else:
			self.sendMessage(200, 'user id doesnt exist')
		conn.commit()
		conn.close()	

	def GetTopLikes(self):
		user_id, _ = cgi.parse_header(self.headers.get('user_id'))
		
		Global.likes_queue.put(user_id)
		self.waitingTurn(user_id, Global.likes_queue)
		
		conn = sqlite3.connect('test.db')
		c = conn.cursor()
		c.execute("INSERT INTO requests VALUES (?, ?, ?, ?)", (str(datetime.datetime.now()), user_id, self.path, ''))
		isIdExist = c.execute('SELECT count(*) FROM tokens where user_id = ? ', (user_id, )).fetchone()[0]
		token = c.execute('SELECT token FROM tokens where user_id = ? order by time desc LIMIT 1', (user_id, )).fetchone()[0]
		conn.commit()
		conn.close()

		if isIdExist != 0:
			top_count = int(self.path.split('?')[-1].split('&')[0].split('=')[1])
			#print(top_count)
			topLikers = getLikeMap(user_id, user_id, token)
			topLikers = sorted(topLikers.items(), key=lambda x: x[1][0], reverse=True)
			self.send_response(200)
			self.end_headers()
			current_user = {}
			users = []
			for user in topLikers[:top_count]:
				current_user['first_name'] = user[1][2]
				current_user['last_name'] = user[1][1]
				current_user['sex'] = user[1][3]
				current_user['bdate'] = user[1][4]
				current_user['photo_url'] = user[1][5]
				current_user['like_count'] = user[1][0]
				current_user['ID'] = user[0]
				users.append(current_user.copy())
				current_user = {}

			response = {}


			response["Users"] = users
			response['code'] = 200

			Global.likes_queue.get()		
			
			self.wfile.write(bytes(json.dumps(response), 'utf-8'))	
		else:
			self.sendMessage(200, 'user id doesnt exist')			



	# The POST part
	def do_POST(self):
		#try:
		if None != re.search('/api/v1/Login/*', self.path):
			self.doLogin()
		elif None != re.search('/api/v1/Logout/*', self.path):
			self.doLogout()
		else:
			self.sendMessage(403, 'inccorrect POST request')

		#except:
		#	print('some exception occured')	
		#	self.sendMessage(400, traceback.format_exc())		


	# The GET part
	def do_GET(self):
		#try:
		if None != re.search('/api/v1/Free/Self/GetInfo/*', self.path):
			self.GetInfo()
		elif None != re.search('/api/v1/Free/Self/GetTopLikes/*', self.path):			
			self.GetTopLikes()
		else:
			self.sendMessage(403, 'inccorrect GET request')
		#except:
		#	print('some exception occured')		
		#	self.sendMessage(400, traceback.format_exc())			

class ThreadedHTTPServer(ThreadingMixIn, HTTPServer):
	allow_reuse_address = True

	def shutdown(self):
		self.socket.close()
		HTTPServer.shutdown(self)

class SimpleHttpServer():

	def __init__(self, ip, port):
		self.server = ThreadedHTTPServer((ip,port), HTTPRequestHandler)

	def start(self):
		self.server_thread = threading.Thread(target=self.server.serve_forever)
		self.server_thread.daemon = True
		self.server_thread.start()

	def waitForThread(self):
		self.server_thread.join()

	def stop(self):
		self.server.shutdown()  
		self.waitForThread()

if __name__=='__main__':
	parser = argparse.ArgumentParser(description='HTTP Server')
	parser.add_argument('port', type=int, help='Listening port for HTTP Server')
	parser.add_argument('ip', help='HTTP Server IP')
	args = parser.parse_args()

	server = SimpleHttpServer(args.ip, args.port)
	print('HTTP Server Running...........')
	server.start()
	server.waitForThread()