	# -*- coding: utf-8 -*-
"""
Created on Mon Nov 11 14:58:36 2019	

@author: Annamarie Kelly
studentID: 13218289
netID: aekelly
"""
from graphics import *
from time import *
import random
import math
import os

#btamt = 0


class balloon(Circle):

	def __init__(self, win, center, radius):
		self.win = win
		Circle.__init__(self, center, radius)

		self.radius = radius
		self.center = center
		self.score = 1

		self.draw_balloons()

		# speed and score generator
		self.speed_scoreGen()

	def draw_balloons(self): # TODO: drawing circle and Line
		self.string = Line(Point(self.center.x,  self.center.y - self.radius), Point(self.center.x, self.center.y - 2 * self.radius))

		self.color = color_rgb(random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))

		self.setFill(self.color)
		self.draw(self.win)
		self.string.setFill("black")
		self.string.draw(self.win)

	def speed_scoreGen(self):  # TODO: Randomly creating speed and score of the balloon ✅
		self.speed = random.random() + 0.1
		self.score = random.randint(1, 10)
		return self.speed

	def getXY(self):  # TODO: Getting center of the circle ✅
		return self.getCenter()

	def move_balloons(self):  # TODO: Move balloon and its string ✅
		dy = self.speed_scoreGen()
		self.move(0, dy)
		self.string.move(0, dy)

	def is_hit(self, pClick):  # TODO: Checking if the clicked point is inside the balloon circle ✅
		center = self.getXY()
		if ((pClick.x - center.x)**2 + (pClick.y - center.y)**2 <= self.radius**2):
			return True
		return False

	def getScore(self): # TODO: return the score of the balloon ✅
		return self.score

	def undraw_balloons(self): # TODO: Undraw balloons and strings ✅
		self.undraw()
		self.string.undraw()


class bug:
	def __init__(self, win):
		# TODO: Create a random point for the center of the bugs ✅
		self.win = win
		self.bug = self
		self.bugCenter = Point(random.randrange(38), random.randrange(29))
		self.BugSpeedX, self.BugSpeedY = 0.7, 0.5

	def loadGIF(self, img): # TODO: load the image and draw it ✅
		self.bug = Image(self.bugCenter, img)
		self.bug.draw(self.win)

	def move_bug(self):

		bug_center = self.bug.anchor
		bug_center.x

		# Checking X
		if bug_center.x <= 1:
			BspeedX = self.BugSpeedX
		elif bug_center.x >= 36:
			BspeedX = -self.BugSpeedX
		else:
			BspeedX = ((math.ceil(0.5 - random.random()) * 2) - 1) * self.BugSpeedX

	# TODO: Add the code to check Y ✅
		if bug_center.y <= 1:
			BspeedY = self.BugSpeedY
		elif bug_center.y >= 26:
			BspeedY = -self.BugSpeedY
		else:
			BspeedY = ((math.ceil(0.5 - random.random()) * 2) - 1) * self.BugSpeedY

	# TODO: Move the bug based on speed ✅
		self.bug.move(BspeedX, BspeedY)

	def undraw_bug(self): # TODO: Undraw bug ✅
		self.bug.undraw()

	def bug_hit(self, pClick):

		# TODO: Checking whether the clicked point is inside the bug image
		# TODO: You can consider the bug like a circle with radius 1
		bug_center = self.bug.anchor
		anchor_offset = 0.5
		xc = bug_center.x - 0.5
		yc = bug_center.y + 0.5
		# treat big as circle find x = 0.5 and x+ 0.5 to find center
		if (xc - pClick.x)**2 + (yc - pClick.y)**2 <= 1:
			return True
		return False


def draw_cloud(win, x1, x2, y1, y2):
	cloud_list = []
	for i in range(11):
		x = random.randint(x1, x2)
		y = random.randint(y1, y2)
		p = Point(x, y)

		cloud = Circle(p, 2)
		cloud.setFill("white")
		cloud.setOutline("white")
		cloud.draw(win)
		cloud_list.append(cloud)
	return cloud_list


def backGround():
	win = GraphWin("Blue sky and green field!", 800, 600)
	win.setCoords(0, 0, 40, 30)

	# set the background color  (width is 40, height is 30)
	BackGround = Rectangle(Point(0, 15), Point(40, 30))
	BackGround.setFill("light blue")
	BackGround.draw(win)

	# set the Ground color
	Ground = Rectangle(Point(0, 0), Point(40, 15))
	Ground.setFill("light Green")
	Ground.draw(win)


	# draw the sun
	sun = Circle(Point(20, 25), 2)
	sun.setFill("Yellow")
	sun.setOutline("Yellow")
	sun.draw(win)

	# draw the mountain1
	Mountain1 = Polygon(Point(0, 15), Point(15, 28), Point(30, 15))
	Mountain1.setFill("dark green")
	Mountain1.draw(win)

	# draw the mountain2
	Mountain2 = Polygon(Point(10, 15), Point(23, 26), Point(40, 15))
	Mountain2.setFill("dark green")
	Mountain2.draw(win)


	# === call draw_cloud function for the left side of the sky ===
	m1 = draw_cloud(win, 5, 10, 20, 21)


	# === call draw_cloud function for the right side of the sky ===
	m2 = draw_cloud(win, 24, 29, 22, 23)

	# === set text for asking user to click a point ===
	pntMsg = Point(20, 29)
	txtMsg = Text(pntMsg, "Please click on one of the balloon.")
	txtMsg.setStyle("bold")
	txtMsg.setTextColor("red")
	txtMsg.draw(win)

	return win

def buttons(win):
	button_1 = Rectangle(Point(2, 3), Point(6, 5))
	button_1.setFill("yellow")
	button_1.draw(win)
	ViewMsg = Text(Point(4, 4), "Start")
	ViewMsg.setStyle("bold")
	ViewMsg.setTextColor("black")
	ViewMsg.draw(win)

	# === stop button ===
	button_2 = Rectangle(Point(7, 3), Point(11, 5))
	button_2.setFill("yellow")
	button_2.draw(win)
	colorMsg = Text(Point(9, 4), "Exit")
	colorMsg.setStyle("bold")
	colorMsg.setTextColor("black")
	colorMsg.draw(win)

	# TODO: === Bet amount Entry ===
	button_3 = Rectangle(Point(12, 3), Point(15, 5))
	button_3.setFill("white")
	button_3.draw(win)
	bet = Entry(Point(13.5, 4), 2)
	text = Text(Point(13.5, 5.5), "Bet $")
	text.setStyle("bold")
	bet.draw(win)
	text.draw(win)
	return button_1, button_2, button_3, bet


def main():
	# creating BG and Buttons
	win = backGround()
	button_1, button_2, button_3, btamt = buttons(win)

	# Number of times user clicked on "start" button
	count = 0
	# TODO: Checking whether the game is started yet or not declare pClick
	restart = 0

	# balance and number of timesare hitted balloons
	balance = 0
	BL_click_count = 0
	betAmount = 1
	#pClick = win.getMouse()

	while True:

		# waiting for user to click on "start" button for the first time
		if restart == 0:
			p_B = win.getMouse()
		else:
			p_B = pClick

		# TODO: Checking click on exit button ✅
		if p_B.x > button_2.p1.x and p_B.y > button_2.p1.y and p_B.x < button_2.p2.x and p_B.y < button_2.p2.y:
			break

		# TODO: Checking click on start button
		elif p_B.x > button_1.p1.x and p_B.y > button_1.p1.y and p_B.x < button_1.p2.x and p_B.y < button_1.p2.y:

			# TODO: if count is 1, then we need to undraw everything and draw them again. ✅
			# otherwise we just need to set count to 1 and draw from beginning.
			if count == 1: #TO DO:
				for BL in BL_list:
					BL.undraw_balloons()
				Buggie.undraw_bug()

			# TODO: Randomly creating number of balloons ✅
			Balloons_No = random.randint(3, 11)
			BL_list = []
			for i in range(Balloons_No):
			# TODO: Generate random x,y AND use balloon class and create BL_list which a list of balloon class objects ✅
				x, y = random.randrange(0, 40), random.randrange(0, 30)
				r = random.randrange(2, 5)
				BL_list.append(balloon(win, Point(x, y), r))

			# TODO: Draw bug using bug class ✅
			Buggie = bug(win)
			Buggie.loadGIF("bug.gif")

			count = 1

			while True:

				# Move balloon and bug
				sleep(0.05)
				for BL in BL_list:
					BL.move_balloons()
					Buggie.move_bug()

				# Waiting for the click
				pClick = None
				while pClick == None:
					pClick = win.checkMouse()
					for BL in BL_list:
						BL.move_balloons()
						Buggie.move_bug()
					sleep(0.05)

				# TODO: Checking if Bug is hitted
				if Buggie.bug_hit(pClick) == True:

					if btamt.getText() == '':
						betAmount = 1
					else:
						betAmount = int(btamt.getText())

					balance += 5 * betAmount
					# TODO: Undraw bug
					Buggie.undraw_bug()
					# TODO: Display remaining balance
					if BL_click_count != 0:
						balance_display.undraw()

					# TODO: Display remaining balance ✅
					balance_display = Text(Point(18, 4), "Balance: $" + str(balance))
					balance_display.setStyle("bold")
					balance_display.draw(win)
					BL_click_count = 1
					continue

				# TODO: checking if a balloon is hitted ✅
				hittedBalloon = -1
				for i in range(Balloons_No):
					if BL_list[i].is_hit(pClick):
						hittedBalloon = i
						if btamt.getText() == '':
							betAmount = 1
						else:
							betAmount = int(btamt.getText())
						win_lose = (math.ceil(0.5 - random.random()) * 2) - 1
						balance += BL_list[hittedBalloon].getScore() * betAmount * win_lose
						break


				# TODO: randomly generate 1 or -1 to decide whether you lose money or win ✅
				# TODO: undraw hitted balloon and use pop(i) to remove it from the list
				Balloons_No -= 1
				if hittedBalloon >= 0:
					if BL_click_count != 0:
						balance_display.undraw()

					# TODO: Display remaining balance ✅
					balance_display = Text(Point(18, 4), "Balance: $" + str(balance))
					balance_display.setStyle("bold")
					balance_display.draw(win)
					BL_click_count = 1
					BL_list[hittedBalloon].undraw_balloons()
					BL_list.pop(hittedBalloon)



				if pClick.x > button_2.p1.x and pClick.y > button_2.p1.y and pClick.x < button_2.p2.x and pClick.y < button_2.p2.y:
					win.close()
					os._exit(0)

				if pClick.x > button_1.p1.x and pClick.y > button_1.p1.y and pClick.x < button_1.p2.x and pClick.y < button_1.p2.y:
					count = 0
					restart = 1
					for balloons in BL_list:
						balloons.undraw_balloons()
					Buggie.undraw_bug()
					balance_display.undraw()
					balance = 0

					break

			# Checking start and exit button
		elif p_B.x > button_2.p1.x and p_B.y > button_2.p1.y and p_B.x < button_2.p2.x and p_B.y < button_2.p2.y:
			win.close()
			os._exit(0)
			# the exit button works so far but just before program is run

		elif p_B.x > button_1.p1.x and p_B.y > button_1.p1.y and p_B.x < button_1.p2.x and p_B.y < button_1.p2.y:
			restart = 1
			count = 0
			break
			# the start button return errors

	win.close()
	os._exit(0)

main()