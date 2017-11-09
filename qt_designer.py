# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'qt_designer.ui'
#
# Created by: PyQt5 UI code generator 5.9
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(800, 600)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.browse = QtWidgets.QPushButton(self.centralwidget)
        self.browse.setGeometry(QtCore.QRect(600, 210, 92, 36))
        self.browse.setObjectName("browse")
        self.filePath = QtWidgets.QLineEdit(self.centralwidget)
        self.filePath.setGeometry(QtCore.QRect(110, 210, 461, 36))
        self.filePath.setObjectName("filePath")
        self.convertToPdf = QtWidgets.QPushButton(self.centralwidget)
        self.convertToPdf.setGeometry(QtCore.QRect(310, 280, 161, 36))
        self.convertToPdf.setObjectName("convertToPdf")
        self.progressBar = QtWidgets.QProgressBar(self.centralwidget)
        self.progressBar.setGeometry(QtCore.QRect(160, 410, 491, 23))
        self.progressBar.setProperty("value", 24)
        self.progressBar.setObjectName("progressBar")
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(170, 390, 91, 20))
        font = QtGui.QFont()
        font.setFamily("Comfortaa")
        self.label.setFont(font)
        self.label.setObjectName("label")
        self.label_2 = QtWidgets.QLabel(self.centralwidget)
        self.label_2.setGeometry(QtCore.QRect(120, 180, 351, 20))
        font = QtGui.QFont()
        font.setFamily("Comfortaa")
        self.label_2.setFont(font)
        self.label_2.setObjectName("label_2")
        self.label_3 = QtWidgets.QLabel(self.centralwidget)
        self.label_3.setGeometry(QtCore.QRect(200, 40, 411, 31))
        font = QtGui.QFont()
        font.setFamily("Comfortaa")
        font.setPointSize(24)
        self.label_3.setFont(font)
        self.label_3.setObjectName("label_3")
        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "LET\'SCAN"))
        self.browse.setText(_translate("MainWindow", "Browse"))
        self.convertToPdf.setText(_translate("MainWindow", "Convert To Pdf"))
        self.label.setText(_translate("MainWindow", "Progress :"))
        self.label_2.setText(_translate("MainWindow", "Choose All the images you want to convert"))
        self.label_3.setText(_translate("MainWindow", "WELCOME TO LET\'SCAN"))


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())

