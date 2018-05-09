# -*- coding: utf-8 -*-

import scrapy
import os
import win32api

class DmozSpider(scrapy.spiders.Spider):
	name = "ishadowsocks"
	allowed_domains = ["ishadowsocks.mobi"]
	start_urls = [
		"http://www.ishadowsocks.mobi/"
	]

	#kill 进程
	os.popen('cmd /c taskkill /f /t /im Shadowsocks.exe','r')
	print ('kill Shadowsocks success ...')

	def parse(self, response):

		_conf=''
		#_fmt='{"server": "%s","server_port": %s,"password": "%s","method": "%s","remarks": "","auth": true,"timeout": 5}'
		_fmt='''
		{
		  "server": "%s",
		  "server_port": %s,
		  "password": "%s",
		  "method": "%s",
		  "remarks": "",
		  "auth": false,
		  "timeout": 5
		}
		'''
		for div in response.xpath('//*[@id="free"]/div/div[2]/div'):
			server=div.xpath('h4[1]/text()').extract()[0].split(':')[1];
			server_port=div.xpath('h4[2]/text()').extract()[0].split(':')[1];
			password=div.xpath('h4[3]/text()').extract()[0].split(':')[1];
			method=div.xpath('h4[4]/text()').extract()[0].split(':')[1];
			if(server.strip()=='' or server_port.strip()=='' or password.strip()=='' or method.strip()==''):
				continue
			if(_conf==''):
				_conf=_fmt % (server,server_port,password,method)
			else:
				_conf+=','+(_fmt % (server,server_port,password,method))


		print('fetch account as : \r\n'+_conf);

		_fmt='''{
	"configs": [
		%s
	],
	"strategy": "com.shadowsocks.strategy.ha",
	"index": -1,
	"global": false,
	"enabled": true,
	"shareOverLan": false,
	"isDefault": false,
	"localPort": 1080,
	"pacUrl": null,
	"useOnlinePac": false,
	"secureLocalPac": true,
	"availabilityStatistics": false,
	"autoCheckUpdate": false,
	"checkPreRelease": false,
	"isVerboseLogging": false,
	"logViewer": {
		"topMost": false,
		"wrapText": false,
		"toolbarShown": false,
		"Font": "Consolas, 8pt",
		"BackgroundColor": "Black",
		"TextColor": "White"
	},
	"proxy": {
		"useProxy": false,
		"proxyType": 0,
		"proxyServer": "",
		"proxyPort": 0,
		"proxyTimeout": 3
	},
	"hotkey": {
		"SwitchSystemProxy": "",
		"SwitchSystemProxyMode": "",
		"SwitchAllowLan": "",
		"ShowLogs": "",
		"ServerMoveUp": "",
		"ServerMoveDown": ""
	}
}
		'''
		_conf= _fmt % _conf
		#print _conf
		f=open('C:/apps/tools/shadowsocks/gui-config.json','w+')
		f.write(_conf)
		f.flush()
		f.close()

		#启动进程
		#x=os.popen('cmd /c C:/apps/tools/shadowsocks/Shadowsocks.exe','r')

			#ShellExecute(hwnd, op , file , params , dir , bShow )
				#hwnd：父窗口的句柄，如果没有父窗口，则为0。
				#op：要进行的操作，为“open”、“print”或者为空。
				#file：要运行的程序，或者打开的脚本。
				#params：要向程序传递的参数，如果打开的为文件，则为空。
				#dir：程序初始化的目录。
				#bShow：是否显示窗口。

		win32api.ShellExecute(0,'open','C:/apps/tools/shadowsocks/Shadowsocks.exe','','',1)
		print ('start Shadowsocks success ...')


