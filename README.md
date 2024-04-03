Software needed to install:
1-	Java 17 
2-	Nodejs
3-	IntelliJ
4-	Docker
5-	Visual Studio Code


Install Java17 :
https://www.oracle.com/ca-en/java/technologies/downloads/#jdk22-windows
Install NodeJs:
	https://nodejs.org/en 


Backend: 

IntelliJ instructions:

1-	Install IntelliJ 
-	IntilliJ download : https://www.jetbrains.com/help/idea/installation-guide.html#snap
2-	Clone GitHub repository : 
Option 1: 
-	Open IntelliJ 
-	Clone the project from VCS 
-	 <img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/bdab8ea9-17e2-4919-b151-70b08d825d75">


-	Copy and paste GitHub repository link : https://github.com/fyodor2021/Arz-fine-foods-final-final-project.git

-	<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/17744132-eaaf-40cf-8f98-1a5552624793">

 

-	Option 2:
-	Clone the project from commandline :
o	git clone https://github.com/fyodor2021/Arz-fine-foods-final-final-project.git
-	open the project in IntelliJ
3-	Load the Gradle  :
Option 1:
When you open the project , it shows a prompt to load the gradle project automatically .

<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/f1296b48-83f5-40b0-81ed-36ac658a8f0e">

 

It should now show on the right of the project as an elephant : 
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/8767d1c3-b756-420d-a95f-aded8e6b390e">



-	Option 2:
Open the backend folder, then click on one of the gradle files , then press on “Link Gradle Project” 
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/3fe15da7-0877-48ff-a086-7f4776f144e5">

 

Navigate to the backend folder, and click open 
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/79df0d62-6b4b-4045-8c45-c69c204e4aef">

 
 It should now appear on the right side of the screen
 <img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/2082f2e8-111d-4917-98c6-6d3c3ae82693">


4-	Set the SDK to java17
File
	Project Structure 
	Select Jave17
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/6396fca4-74f2-42ac-b335-cb39677eb835">

 

Docker Instructions:


1-	Install Docker Dashboard: https://www.docker.com/get-started/ 
2-	Open the software 
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/7521b66d-3775-4095-9ac7-b4c29f25738a">


 
3-	Go back to IntelliJ , open the terminal , and run the following two commands:
cd backend
docker-compose up
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/1af25c71-55c8-486f-a4a0-4b1d6c6e7ef6">

 
The dashboard should look like this :
 
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/87261fd5-e408-479e-adbe-c30a6cc9b536">

Note: if any of the containers are not running, just turn them on again
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/b46a2814-2802-44e3-be5c-aed2ecaa81ec">


Running the micro-services instructions:
1-	Navigate to the discovery microservice
	  DiscoveryMicroservieApplication 
	And click on the start button 
	
 <img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/4874b863-8473-4482-b7e4-b726cbf3d873">


Repeat for all other microservices, until they are all up and running
They should be a total of 10 micro-services related to the customer pov

1-	Discovery-microservice
2-	Api-gateway 
3-	Authentication-microservice
4-	Product-microservice
5-	Cart- microservice
6-	Points- microservice
7-	Wallet- microservice
8-	review microservice
9-	inventory-microservice
10-	order-microservice

Note : please note that discovery-microservice should be the first to run, then you can run the other microservices.
<img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/20740138-4ee3-48ee-94f4-1a9561f69dc3">



 


Frontend:
VS studio instructions: 
1-	Install Visual Studio Code https://code.visualstudio.com/download 
2-	Open vs code, select file 
	Open 
	Navigate to the project file , then frontend 
	Select open
 <img width="468" alt="image" src="https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/f80cf380-e535-427c-81f9-4caf856ae0a0">


3-	Open the terminal in vs studio 
Terminal 
	New terminal 

Run the following commands :
	Npm install 
	Npm start

The website should open on your browser, you can navigate through the website as a guest or user if you would like to register.

![image](https://github.com/fyodor2021/Arz-fine-foods-final-final-project/assets/123840261/83e8e7f5-3f85-40e0-b672-d88dea0ce94f)
