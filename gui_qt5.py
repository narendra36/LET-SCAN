import sys
from PyQt5.QtWidgets import QApplication, QWidget, QInputDialog, QLineEdit, QLabel,QFileDialog, QPushButton, QGridLayout, QProgressBar
from PyQt5.QtGui import QIcon
 
class App(QWidget):
 
    def __init__(self):
        super().__init__()
        self.left = 10
        self.top = 10
        self.width = 640
        self.height = 480
        self.lineEdit = QLineEdit()
        self.initUI()


    def openFileNameDialog(self):    
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        fileName, _ = QFileDialog.getOpenFileNames(self,"QFileDialog.getOpenFileName()", "","All Files (*);;PNG Files (*.png);;Jpg Files (*.jpg)", options=options)
        if fileName[0]:
            self.path = fileName
            self.lineEdit.setText(fileName[0])
            print(self.path)
            #print(fileName)

    def convertPrograss(self):
        self.completed = 0

        while self.completed < 100:
            self.completed += 0.0001
            self.progress.setValue(self.completed)

    def initUI(self):
        self.setWindowTitle('LET\'SCAN ')  
        self.setGeometry(self.left, self.top, self.width, self.height)

        btn = QPushButton('Browse', self)
        btn.resize(btn.sizeHint())
        btn.move(50, 50)            
        btn.clicked.connect(self.openFileNameDialog)

        ctop = QPushButton('Convert to PDF', self)
        ctop.resize(btn.sizeHint())  
        ctop.clicked.connect(self.convertPrograss)        

        self.progress = QProgressBar(self)
        self.pLabel = QLabel(' Progress', self)
        # grid layout
        grid = QGridLayout()
        grid.setSpacing(10)
        grid.addWidget(self.lineEdit, 1, 0)
        grid.addWidget(btn, 1, 1)
        grid.addWidget(ctop, 2,0,)
        grid.addWidget(self.pLabel, 3,1)
        grid.addWidget(self.progress, 3,0)
        self.setLayout(grid)          
       
        self.show()
 

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    sys.exit(app.exec_())