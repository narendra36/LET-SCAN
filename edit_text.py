from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(800, 600)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.completeText = QtWidgets.QPlainTextEdit(self.centralwidget)
        self.completeText.setGeometry(QtCore.QRect(70, 70, 651, 391))
        self.completeText.setObjectName("completeText")
        self.fileName = QtWidgets.QLineEdit(self.centralwidget)
        self.fileName.setGeometry(QtCore.QRect(110, 490, 391, 36))
        self.fileName.setObjectName("fileName")
        self.createPdfButton = QtWidgets.QPushButton(self.centralwidget)
        self.createPdfButton.setGeometry(QtCore.QRect(540, 490, 141, 36))
        self.createPdfButton.setObjectName("createPdfButton")
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(330, 30, 131, 20))
        font = QtGui.QFont()
        font.setFamily("Comfortaa")
        font.setPointSize(18)
        self.label.setFont(font)
        self.label.setObjectName("label")
        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "Edit & Create PDF"))
        self.createPdfButton.setText(_translate("MainWindow", "Create PDF"))
        self.label.setText(_translate("MainWindow", "LET\'SCAN"))

