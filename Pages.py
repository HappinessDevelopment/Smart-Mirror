import tkinter as tk

class Page(tk.Frame):
	def_init_(self, *args, **kwargs)
                tk.Frame._init_(self, *args, **kwargs)
	def show(self):
                self.lift()

class Home(Page):
	def _init_(self, *args, **kwargs)
		Page._init_(self, *args, **kwargs)
		label = tk.Label(self, text = "Home screen")
		label.pack(side = "top", fill = "both", expand = True)


class Weather(Page):
	def _init_(self, *args, **kwargs)
		Page._init_(self, *args, **kwargs)
		label = tk.Label(self, text = "Weather module")
		label.pack(side = "top", fill = "both", expand = True)

class Events(Page):
	def_init_(self, *args, **kwargs)
		Page._init_(self, *args, **kwargs)
		label = tk.Label(self, text = "Events page")
		label.pack(side = "top", fill = "both", expand = True)

class MainView(tk.Frame):
	def_init_(self, *args, **kwargs)
		tk.Frame._init_(self, *args, **kwargs)
		p1 = Home(self)
		p2 = Weather(self)
		p3 = Events(self)

		buttonframe = tk.Frame(self)
		container = tk.Frame(self)
		buttonframe.pack(side="top", fill="x", expand= False)
		container.pack(side="top", fill = "both", expand = True)

		p1.place(in_=container, x=0, y=0, relwidth=1, relheight=1)
		p2.place(in_=container, x=0, y=0, relwidth=1, relheight=1)
		p3.place(in_=container, x=0, y=0, relwidth=1, relheight=1)

		b1 = tk.Button(buttonframe, text="Home", command = p1.lift)
		b2 = tk.Button(buttonframe, text="Weather", command = p2.lift)
		b3 = tk.Button(buttonframe, text="Events", command = p3.lift)

		b1.pack(side = "left")
		b2.pack(side = "left")
		b3.pack(side = "left")

		p1.show()

if _name_ == "_main_":
	root = tk.Tk()
	main = MainView(root)
	main.pack(side="top", fill="both", expand = True)
	root.wm_geometry("400x400")
	root.mainloop()
