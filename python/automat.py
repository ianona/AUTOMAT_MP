import wx
import time

class State():
    def __init__(self, transition, earth, mars):
        self.transition = ''
        if len(transition) == 0:
            self.transition = "none"
        else:
            for i in transition:
                self.transition += i

        self.left = ''
        for i in earth:
            self.left += i.type

        self.right = ''
        for i in mars:
            self.right += i.type

class Item(wx.StaticBitmap):
    def __init__(self, type, parent, onRocket, position, filename):
        super(Item, self).__init__(parent, -1, wx.Bitmap(wx.Image(filename, wx.BITMAP_TYPE_ANY).Scale(50,50)),position)

        self.filename = filename
        self.type=type
        self.onRocket = onRocket

    def __repr__(self):
        return self.type

    def __str__(self):
        return self.type

    def SetNewParent(self, p):
        self.SetParent(p)

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
        self.EARTH = []        
        self.MARS = []
        self.transition = []
        self.states = []
        # add start state
        self.states.append(State(self.transition,self.EARTH,self.MARS))

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

        # ROCKET PANEL
        self.rocketPnl = wx.Panel(self.mainPanel,-1,(self.X,self.Y),(255,155))

        # ROCKET
        rocketImg = wx.Image("rocket"+extension, wx.BITMAP_TYPE_ANY)
        rocketImg = wx.Bitmap(rocketImg)
        self.rocket = wx.StaticBitmap(self.rocketPnl, -1, rocketImg, (0,0))

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

        '''
        # add icons
        self.leftIcons = 0
        for i in self.EARTH:
            self.addIcon("L",i.filename)
        '''

        # add to earth
        self.EARTH.append(self.addIcon("H","L","boy"+extension))
        self.EARTH.append(self.addIcon("H","L","girl"+extension))
        self.EARTH.append(self.addIcon("L","L","lion"+extension))
        self.EARTH.append(self.addIcon("C","L","cow"+extension))
        self.EARTH.append(self.addIcon("R","L","rice"+extension))
        
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

    def addIcon(self,type,side,filename):
        # ADD ICONS TO APPROPRIATE PANEL
        if side == "L":
            item = Item(type, self.mainPanel, False, ((len(self.EARTH)) * 50,325), filename)
            item.Bind(wx.EVT_LEFT_DOWN,self.clickItem)
            return item
        elif side == "R":
            item = Item(type, self.mainPanel, False, ((len(self.EARTH)*50) + 440,325), filename).Bind(wx.EVT_LEFT_DOWN,self.clickItem)
            item.Bind(wx.EVT_LEFT_DOWN,self.clickItem)
            return item

    def clickItem(self,e):
        item = e.GetEventObject()
        if not item.onRocket:
            self.moveToRocket(item)
            item.onRocket = True

    def moveToRocket(self, Item):
        #Item.Parent = None
        #Item.SetPosition((100,200))
        print("PORK")

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
                self.rocketPnl.Move(self.X,self.Y)
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
                self.rocketPnl.Move(self.X,self.Y)
            else:
                self.timer.Stop()
                self.currentPosition = "L"

                # adds new state everytime it returns to earth
                # add new state and refresh
                self.states.append(State(self.transition,self.EARTH,self.MARS))
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
