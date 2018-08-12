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

        # intialize GUI
        self.initialize()

    def initialize(self):
        self.SetSize(700,500)
        self.mainPanel = wx.Panel(self)
        self.SetTitle("AUTOMAT MP")
        #self.SetBackgroundColour("WHITE")

        btn = wx.Button(self.mainPanel,label="ANIMATE",pos=(0,0),size=(100,50))
        btn.Bind(wx.EVT_LEFT_DOWN, self.animate)

        # PANEL FOR FSM
        self.FSM = wx.Panel(self.mainPanel,-1,pos=(self.X,self.Y),size=(100,100))
        self.FSM.SetBackgroundColour("RED")

        # PANELS
        pnlImg = wx.Image("lion.png", wx.BITMAP_TYPE_ANY)
        pnlImg = wx.Bitmap(pnlImg)
        self.rightPnl = wx.StaticBitmap(self.FSM, -1, pnlImg, (0,0))

        #self.Centre() 
        self.Show(True)
        self.SetPosition((300,200))

    def animate(self,e):
        self.timer.Start(10)

    def onTimer(self,e):
        if self.currentPosition == "L":
            if self.X != 450:
                self.X+=10
                self.FSM.Move(self.X,self.Y)
            else:
                self.timer.Stop()
                self.currentPosition = "R"
        elif self.currentPosition == "R":
            if self.X != 30:
                self.X-=10
                self.FSM.Move(self.X,self.Y)
            else:
                self.timer.Stop()
                self.currentPosition = "L"


def main():
	app = wx.App()
	MainFrame(None)
	app.MainLoop()

main()
