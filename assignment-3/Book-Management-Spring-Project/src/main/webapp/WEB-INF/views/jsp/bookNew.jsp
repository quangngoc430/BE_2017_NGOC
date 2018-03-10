<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Book New</title>
    <style>
        .form-createNewBook{
            border-radius: 5px;
            background-color: white;
            width: 100%;
            max-width: 500px;
            margin:150px auto;
            padding: 50px;
        }
    </style>
</head>
<body style="background-color: #E0E0E0">
    <%@ include file="_header.jsp"%>

    <div class="body">
        <%@ include file="_message.jsp"%>
        <form:form action="/book/new" method="post" modelAttribute="book" cssClass="form-createNewBook" enctype="multipart/form-data">
            <h1 class="h3 mb-3 font-weight-normal" align="center">Create new book</h1>

            <div align="center">
                <img id="bookCoverImage" src="" class="h3 mb-3 center-block" style="width: 200px; height: 200px; border-radius: 50%; border: 1px solid black;" />
            <div/>

            <div class="form-label-group">
                <form:input path="title" cssClass="form-control" placeholder="Title"/>
                <form:errors path="title" cssClass="error"/>
                <label for="title">Title</label>
            </div>
            <div class="form-label-group">
                <form:input path="author" cssClass="form-control" placeholder="Author"/>
                <form:errors path="author" cssClass="error"/>
                <label for="author">Author</label>
            </div>
            <div class="form-label-group">
                <form:textarea path="description" cssClass="form-control" rows="5" placeholder="Description"/>
                <form:errors path="description" cssClass="error"/>
                <label for="description">Description</label>
            </div>

            <input id="uploadImage" name="bookCoverImage" class="form-control-file" type="file" onchange="PreviewImage();"/>

            <input type="submit" class="btn btn-lg btn-primary btn-block" name="submit" value="Create"/>
        </form:form>
    </div>

    <%@ include file="_footer.jsp"%>

    <script type="text/javascript">

        function PreviewImage() {
            var oFReader = new FileReader();
            oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

            oFReader.onload = function (oFREvent) {
                document.getElementById("bookCoverImage").src = oFREvent.target.result;
            };
        };

        $('#bookCoverImage').on('click', function(){
            $('#uploadImage').trigger('click');
        });

    </script>

    <style>
        #uploadImage{
            display: none;
        }
    </style>
</body>
</html>
