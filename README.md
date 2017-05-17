<img src="https://docs.chrisv.be/DTE/DTE.svg" alt="DTE Logo" width="100%" height="144">

___
# DTE ( Digital Temperature Equipment )
[EPHEC](http://www.ephec.be/cours-du-jours/nos-formations/informatique-3) - 2016/17 - T207 Digital Electronics, signal management. - Group 05

## Team
- **Chris Van Waesberghe** ([contact@chris.be](mailto:contact@chrisv.be)) - JAVA
- **Nathan Dolinski** ([n.dolinski@students.ephec.be](n.dolinski@students.ephec.be)) - PROTEUS / EAGLE
- **Mathieu Rousseau** ([m.rousseau@students.ephec.be](m.rousseau@students.ephec.be)) - PROTEUS / EAGLE / CCS
- **Amélie Courtin** ([a.courtin@students.ephec.be](a.courtin@students.ephec.be)) - WELDS / JAVA

## Warning
**Digital Temperature Equipment** was a school project (2016/17). It may never be updated in the future. Use it a your own risk. However feel free to submit a pull request !

## Finality of the project
* Elaborate a working methodology and plan the activities by realizing a project based on **interfacing a computer system with an external environment**
* Analyze a given technical situation and **propose solutions that take account of the constraints**
* **Implement theoretical knowledge** acquired in first year and second year electronics courses.
* Approach the design and **manufacturing process of an electronic product**
* Carry out the work requested on the basis of the specifications

## Instructions & Objectives
* **Establish a working plan** that breaks down the project into main stages and, if necessary, in sub-steps, with an estimate of the resources needed (material and human), the time of implementation and the date of completion
* **Show the progress of the project** at the beginning of March with a written report (from 2 to 3 pages). The report must be submitted no later than March 6, 2017. Students must also suggest the suggested paths for further development of the project.
* **Present and defend this project orally** in front of Mr Bouterfa and Mr Dewulf the last week of the semester course.

## Specifications
### Product to design
Create a PCB card with a PIC getting an analog input and defining the state of several digital outputs, which also includes the probe and on the other hand a temperature display alongside an alarm information.

### Hardware Specifications
* PIC : **18F458**
  * Programmable via the RS232 interface
* Temperature sensor : **LM35**
  * The sensor must operate within the temperature range of 0 °C to 100 °C.
  * The use of the sensor for negative temperatures can be implemented but is not mandatory.
* Display of the temperature: it must be done on two 7-segment displays with common cathodes. These displays are located on the board alongside the probe.
* Alert: It is indicated by a red LED flashing.
* When no alert is in progress, a green LED must remain permanently on.

### Software specifications
* **JAVA Interface** :
  * Must display whether an alert is in progress or not.
  * Doit envoyer au PIC la valeur de la nouvelle température de seuil à laquelle l'alerte sera déclenchée.
  * It communicates with the PIC via the RS232 port of the PC.
    * The API "[RxTx](http://users.frii.com/jarvi/rxtx/)" will be used to control the RS232 port in java ([WIKI](http://rxtx.qbang.org/wiki/index.php/Main_Page))

### Softwares et librairies
  * **Simulateur électronique** : Proteus ISIS
  * **Dessin électronique** : Cadsoft Eagle
  * **Compilateur C pour PIC** : CCS PCWH Compiler ou autre (MPLAB® X IDE)
  * **Java** : Eclipse / Netbeans / IntelliJ IDEA or other
  * **Simulation de la liaison série** : Virtual Serial Ports Emulator
  * **Librairie** : "[RxTx](http://users.frii.com/jarvi/rxtx/)" to manage communication on COM port in java

## Licence
![GPL3](https://camo.githubusercontent.com/3eb103d4afbd5bb2bbdf3d03e0e23e05ef44190f/687474703a2f2f7777772e676e752e6f72672f67726170686963732f67706c76332d3132377835312e706e67)

Copyright 2017
- **Chris Van Waesberghe** ([contact@chris.be](mailto:contact@chrisv.be)) - JAVA
- **Nathan Dolinski** ([n.dolinski@students.ephec.be](n.dolinski@students.ephec.be)) - PROTEUS / EAGLE
- **Mathieu Rousseau** ([m.rousseau@students.ephec.be](m.rousseau@students.ephec.be)) - PROTEUS / EAGLE / CCS
- **Amélie Courtin** ([a.courtin@students.ephec.be](a.courtin@students.ephec.be)) - WELDS / JAVA

The DTE Java Interface (and other components of the project) are free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. The DTE Java Interface is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with the DTE Java Interface and the whole DTE Project.  If not, see <http://www.gnu.org/licenses/>.
