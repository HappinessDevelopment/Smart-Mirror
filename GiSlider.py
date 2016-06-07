#imports
import tkinter as tk
from gi.repository import Gtk
from urllib.request import urlopen
import RPi.GPIO as GPIO
import pyautogui
import json
import time
import threading

class StackWindow(Gtk.Window):

    
    #this function creates the window
    def __init__(self):
        Gtk.Window.__init__(self, title="Smart Mirror")
        self.set_border_width(10)
        vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
        self.add(vbox)
        #this is where i would change the bg color to black, but idk how. :(

        #stack holding all the frames (pages)
        stack = Gtk.Stack()
        stack.set_transition_type(Gtk.StackTransitionType.SLIDE_LEFT_RIGHT)
        stack.set_transition_duration(1000)    

        #date/time retrieval
        now = (time.strftime("%I:%M"))
        today = (time.strftime("%A"))
        date = (time.strftime("%B %d"))
        
        #weather (Wunderground API) retrieval

        #API key
        key = "9cd32fa160722b58"

        #accesses the URL via API key and uses the json
        url = 'http://api.wunderground.com/api/' + key + '/geolookup/conditions/q/PA/98011.json'
        f = urlopen(url)
        json_string = f.read()
        parsed_json = json.loads(json_string.decode())
        city = parsed_json['location']['city']
        state = parsed_json['location']['state']
        weather = parsed_json['current_observation']['weather']
        temperature_string = parsed_json['current_observation']['temperature_string']
        f.close()

        #First page
        label1 = Gtk.Label()
        label1.set_markup('<big>' + today + ', ' + date + '\n' + now + '\n</big>' + city + ', ' + state + '\n' + temperature_string)
        stack.add_titled(label1, "label2", "Home")

        #second page
        label2 = Gtk.Label()
        label2.set_markup("<big>Weather\n\n</big>" + weather.title() + '.\nThe temperature is ' + temperature_string)
        stack.add_titled(label2, "label3", "Weather")

        #third page
        label3 = Gtk.Label()
        label3.set_markup("<big>Events</big>")
        stack.add_titled(label3, "label4", "Events")

        #fourth page
        label4 = Gtk.Label()
        label4.set_markup("<big>Traffic</big>")
        stack.add_titled(label4, "label5", "Traffic")

        #fifth page
        label5 = Gtk.Label()
        label5.set_markup("<big>Bus Schedule</big>")
        stack.add_titled(label5, "label1", "Bus Schedule")

        #switches between frames
        stack_switcher = Gtk.StackSwitcher()
        stack_switcher.set_stack(stack)
        vbox.pack_start(stack_switcher, True, True, 0)
        vbox.pack_start(stack, True, True, 0)



win = StackWindow()
win.connect("delete-event", Gtk.main_quit)
win.show_all()
Gtk.main()
