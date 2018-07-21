import wx
import time

class State():
    def __init__(self, transition, left, right):
        self.transition = transition
        self.left = left
        self.right = right

class MainFrame(wx.Frame):
    def __init__(self, *args, **kwargs):
        style = wx.DEFAULT_FRAME_STYLE
        super(MainFrame, self).__init__(*args, **kwargs, style = style)

        # to be used for animating
        self.timer = wx.Timer(self, -1)
        self.Bind(wx.EVT_TIMER, self.onTimer)
        self.currentPosition = "L"
        self.X = 30
        self.Y = 150

        # to be used for FSM
        self.left = "BGLCR"
        self.right = ""
        self.transition = None
        self.states = []
        # add start state
        self.states.append(State(self.transition,self.left,self.right))

        # intialize GUI
        self.initialize()

    def initialize(self):
        self.SetSize(700,500)
        self.mainPanel = wx.Panel(self)
        self.SetTitle("AUTOMAT MP")
        self.SetBackgroundColour("WHITE")

        # change this to .png for hq images, .gif for lq
        extension = ".png"
        
        # BACKGROUND PHOTO
        bgImg = wx.Image("bg"+extension, wx.BITMAP_TYPE_ANY).Scale(700,500)
        bgImg = wx.Bitmap(bgImg)
        self.bg = wx.StaticBitmap(self.mainPanel, -1, bgImg, (0,0), (700,500))

        # ROCKET
        rocketImg = wx.Image("rocket"+extension, wx.BITMAP_TYPE_ANY)
        rocketImg = wx.Bitmap(rocketImg)
        self.rocket = wx.StaticBitmap(self.mainPanel, -1, rocketImg, (self.X,self.Y))

        # GO RIGHT BUTTON
        rightBtnImg = wx.Image("Rbtn"+extension, wx.BITMAP_TYPE_ANY)
        rightBtnImg = wx.Bitmap(rightBtnImg)
        self.rightBtn = wx.StaticBitmap(self.mainPanel, -1, rightBtnImg, (305,300))
        self.rightBtn.Bind(wx.EVT_LEFT_DOWN, self.animate)

        # GO LEFT BUTTON
        leftBtnImg = wx.Image("Lbtn"+extension, wx.BITMAP_TYPE_ANY)
        leftBtnImg = wx.Bitmap(leftBtnImg)
        self.leftBtn = wx.StaticBitmap(self.mainPanel, -1, leftBtnImg, (305,300))
        self.leftBtn.Bind(wx.EVT_LEFT_DOWN, self.animate)
        self.leftBtn.Hide()

        # PANELS
        pnlImg = wx.Image("panel2"+extension, wx.BITMAP_TYPE_ANY)
        pnlImg = wx.Bitmap(pnlImg)
        self.rightPnl = wx.StaticBitmap(self.mainPanel, -1, pnlImg, (410,310))

        pnlImg = wx.Image("panel"+extension, wx.BITMAP_TYPE_ANY)
        pnlImg = wx.Bitmap(pnlImg)
        self.leftPnl = wx.StaticBitmap(self.mainPanel, -1, pnlImg, (0,310))

        # ICONS
        self.leftIcons = 0
        self.human1 = self.addIcon(self.currentPosition,"boy",extension)
        self.human2 = self.addIcon(self.currentPosition,"girl",extension)
        self.lion = self.addIcon(self.currentPosition,"lion",extension)
        self.cow = self.addIcon(self.currentPosition,"cow",extension)
        self.rice = self.addIcon(self.currentPosition,"rice",extension)

        # GRAY PANELS
        pnlImg = wx.Image("grayPnl2"+extension, wx.BITMAP_TYPE_ANY)
        pnlImg = wx.Bitmap(pnlImg)
        self.XrightPnl = wx.StaticBitmap(self.mainPanel, -1, pnlImg, (410,310))
        self.XrightPnl.Show()

        pnlImg = wx.Image("grayPnl"+extension, wx.BITMAP_TYPE_ANY)
        pnlImg = wx.Bitmap(pnlImg)
        self.XleftPnl = wx.StaticBitmap(self.mainPanel, -1, pnlImg, (0,310))
        self.XleftPnl.Hide()

        # PANEL FOR FSM
        self.FSM = wx.Panel(self.mainPanel,-1,pos=(0,390),size=(700,100))
        self.FSM.Bind(wx.EVT_PAINT, self.drawStates) 

        self.Centre() 
        self.Show(True)
        self.SetPosition((300,200))

    def addIcon(self,side,file,extension):
        ico = wx.Image(file+extension, wx.BITMAP_TYPE_ANY).Scale(50,50)
        ico = wx.Bitmap(ico)

        # ADD ICONS TO APPROPRIATE PANEL
        if side == "L":
            self.leftIcons += 1
            return wx.StaticBitmap(self.mainPanel, -1, ico, ((self.leftIcons-1) * 50,325))

    def animate(self,e):
        self.timer.Start(10)

    def onTimer(self,e):
        # if currently left, animate going to the right
        if self.currentPosition == "L":
            self.rightBtn.Hide()
            self.leftBtn.Show()
            self.XleftPnl.Show()
            self.XrightPnl.Hide()
            if self.X != 450:
                self.X+=10
                self.rocket.Move(self.X,self.Y)
            else:
                self.timer.Stop()
                self.currentPosition = "R"
        # if currently right, animate going to the left
        else:
            self.leftBtn.Hide()
            self.rightBtn.Show()
            self.XleftPnl.Hide()
            self.XrightPnl.Show()
            if self.X != 0:
                self.X-=10
                self.rocket.Move(self.X,self.Y)
            else:
                self.timer.Stop()
                self.currentPosition = "L"

                # adds new state everytime it returns to earth
                # add new state and refresh
                self.states.append(State(self.transition,self.left,self.right))
                self.Refresh()

    def drawStates(self,e):
        self.dc = wx.PaintDC(self.FSM) 
        color = wx.Colour(255,153,69)
        self.dc.SetBrush(wx.Brush(color)) 

        for i in range(0,len(self.states)):
            curState = self.states[i]

            # DRAW CIRCLE
            self.dc.DrawCircle((i*100)+70,40,30) 
            
            # FIX TEXT FOR THE STATE
            left = "empty"
            if curState.left != "":
                left = curState.left
            right = "empty"
            if curState.right != "":
                right = curState.right

            # DRAW STATE TEXT
            font = wx.Font(11, wx.MODERN, wx.NORMAL, wx.NORMAL) 
            self.dc.SetFont(font) 
            self.dc.DrawText(left + "/\n" + right,(i*100)+50,30) 

            # FIX TRANSITION TEXT
            if curState.transition is not None:
                transition = curState.transition
            else:
                transition = "start"
            # DRAW TRANSITION
            self.dc.DrawText(transition,(i*100)+5,30)
            self.dc.DrawLine((i*100),40,(i*100)+40,40) 

def main():
	app = wx.App()
	MainFrame(None)
	app.MainLoop()

main()
