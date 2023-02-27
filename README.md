# PDF-Generator

 The application provides REST APIs that can be used to generate and download PDF documents based on user input.


Features

Users can generate and download PDF documents by providing input data.
The application makes use of an external library to generate the PDF documents.
Users can specify the filename for the generated PDF document.
Users can also download previously generated PDF documents by specifying the document ID and filename.


Installation

Clone the repository from GitHub:
git clone https://github.com/rajkumarDhumal/PDF-Generator.git
Open the project in your preferred IDE.
Build and run the application.


Usage

The application provides the following REST APIs:

Generate and Download PDF:

URL: http://localhost:8080/pdf/generate/{filename}
Method: POST
Request Body: The request body should contain the data required to generate the PDF document.
Path Variables:
filename: The name to be assigned to the generated PDF file.
Response Body: The generated PDF file in bytes.
Response Headers:
Content-Disposition: Attachment with the specified filename.
Content-Type: application/pdf
Notes: This endpoint generates and downloads the PDF document based on the provided data.


Download PDF:

URL: http://localhost:8080/pdf/download/{id}/{filename}
Method: GET
Path Variables:
id: The ID of the previously generated PDF document.
filename: The name to be assigned to the downloaded PDF file.
Response Body: The downloaded PDF file in bytes.
Response Headers:
Content-Disposition: Attachment with the specified filename.
Content-Type: application/pdf
Notes: This endpoint downloads the previously generated PDF document with the specified ID and filename.


API Documentation

The application makes use of Swagger to provide API documentation. To view the documentation, navigate to http://localhost:8080/swagger-ui.html in your browser. This will display the API documentation page, where you can view the available endpoints and their descriptions.


Technologies Used

Spring Boot,
Swagger,
Java, thymeleaf, itextpdf, MySql
