#Uses the Wunderground API to retrieve information about the current weather conditions in Bothell

from urllib.request import urlopen
import json
key = "9cd32fa160722b58"
import tkinter as tk

#ask user input for zip code desired
zip = input('For which ZIP code would you like to see the weather?')

#grabs info via urlopen() and uses the API ft. our API key and zip code from user. Uses json.
url = 'http://api.wunderground.com/api/' + key + '/geolookup/conditions/q/PA/' + zip + '.json'

#open file
f = urlopen(url)
json_string = f.read()
parsed_json = json.loads(json_string.decode())
city = parsed_json['location']['city']
state = parsed_json['location']['state']
weather = parsed_json['current_observation']['weather']
temperature_string = parsed_json['current_observation']['temperature_string']

#print retrieved data
print ('Weather in ' + city + ', ' + state +  ': ' + weather.lower() + '. The temperature is ' + temperature_string)

#close file
f.close()

#create GUI window
root = tk.Tk()

#modify root window
root.title("Weather")
root.geometry("2000x1000")
label = tk.Label(root, text = "Our Weather Widget")

#kick off event loop
root.mainloop()
    
