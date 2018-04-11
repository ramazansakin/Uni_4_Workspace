<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ProjeAnaSayfa.aspx.cs" Inherits="BlogPortalProjesi.ProjeAnaSayfa" %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Blog Portal - Kapak Sayfası</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/cover.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            background-color:#FFFFFF;
        }
    </style>
  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
         </div>

          <div class="inner cover">
            <h1 class="cover-heading">Web Projesi Yönetimi</h1>
              <div class="panel panel-default">
                   <div class="panel-heading">
                       <h3 class="panel-title">BLOG PORTAL</h3>
                   </div>
             <div class="panel-body">
                <div class="list-group">
                    <a href="javascript:onclick(alert('Öğrenci Numarası : 201513171801'));" class="list-group-item active">
                        <h4 class="list-group-item-heading">Önder Coşkun</h4>
                        <p class="list-group-item-text">Kodlama - Tasarım</p>
                    </a>
                </div>
                 <div class="list-group">
                    <a href="javascript:onclick(alert('Öğrenci Numarası : 201413171809'))" class="list-group-item active">
                        <h4 class="list-group-item-heading">Ramazan Sakin</h4>
                        <p class="list-group-item-text">Kodlama - Tasarım</p>
                    </a>
                </div>
                 <div class="list-group">
                    <a href="javascript:onclick(alert('Öğrenci Numarası : 201413171804'))" class="list-group-item active">
                        <h4 class="list-group-item-heading">Mustafa Altınay</h4>
                        <p class="list-group-item-text">Kodlama - Tasarım</p>
                    </a>
                </div>
            </div>
         </div>
            <p class="lead">
              <a href="Anasayfa.aspx" class="btn btn-lg btn-default">Giriş Sayfasına Git!</a>
            </p>
          </div>

          <div class="mastfoot">           
          </div>

        </div>

      </div>

    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="Scripts/bootstrap.min.js"></script>
  </body>
</html>

