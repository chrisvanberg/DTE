<img src="https://docs.chrisv.be/dte/dte.svg" alt="DTE Logo" width="100%" height="144">

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

## How to launch the simulation
1. Prerequisites
    1. The latest [JAVA runtime](https://www.java.com/fr/download/) update installed
    1. [Proteus 8](https://www.labcenter.com/)
1. Preset
    1. Clone or download the project
    1. Build or [download](https://github.com/KeydownR/DTE/releases) the binaries of the DTE JAVA interface
    1. Download and install the [**RXTX library**](http://fizzed.com/oss/rxtx-for-java) on your computer
    1. Create a COM virtual pair (Ex. COM1<->COM2) using for example [**VSPE** (Windows)](http://www.lawyerment.com/downloads/Programming/Debugging_and_Tracing/Review_17462_index.htm)
1. Launching the simulation
    1. Launch the DTE Java interface
    1. Connect to one of the COM port that you have paired earlier
    1. Open the PROTEUS project
    1. Be sure that the PIC source code is `sonde2.hex` (Double clic on the PIC and check the value)
    1. Click on the RS232 connector and selec the other COM port that you have paired earlier
    1. Start the PROTEUS simulation
1. Test the simulation
    1. You can change the threshold temperature on the JAVA interface
    1. You can change the current temperature on PROTEUS by clicking on the `+ / -` near the temperature probe
    1. The green led indicate that everything is fine
    1. The blinking red led indicate that the current temperature is beyond the threshold and an alert is triggered


## Licence
![GPL3](https://camo.githubusercontent.com/3eb103d4afbd5bb2bbdf3d03e0e23e05ef44190f/687474703a2f2f7777772e676e752e6f72672f67726170686963732f67706c76332d3132377835312e706e67)

Copyright 2017
- [**Chris Van Waesberghe**](mailto:contact@chrisv.be) - JAVA
- [**Nathan Dolinski**](mailto:n.dolinski@students.ephec.be) - PROTEUS / EAGLE
- [**Mathieu Rousseau**](mailto:m.rousseau@students.ephec.be) - PROTEUS / EAGLE / CCS
- [**Amélie Courtin**](mailto:a.courtin@students.ephec.be) - WELDS / JAVA

The DTE Java Interface (and other components of the project) are free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. The DTE Java Interface is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with the DTE Java Interface and the whole DTE Project.  If not, see <http://www.gnu.org/licenses/>.
