<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="adminPanel.aspx.cs" Inherits="BlogPortalProjesi.adminPanel" %>
<%@ Import Namespace="BlogPortalProjesi" %>
<%@ Import Namespace="System.Data" %>
<%@ Import Namespace="System.Data.SqlClient" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Yönetici Paneli</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="admin.css" rel="stylesheet">
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
      <style>
          .btn-link {
              color:#777;
              padding-top:15px;
              padding-bottom:15px;
          }
          .btn-link:hover {
              text-decoration:none;
          }
          a {
              color:#FFFFFF;
          }
          a:visited {
              color:#FFFFFF;
          }
          a:hover {
              color:#FFFFFF;
              text-decoration:underline;
          }
          .istatistik {
              padding:5px;
              font-size:12px;
              width:400px !important;
          }
          .istatistik2 {
              padding:5px;
              font-size:12px;
              margin-left:20px;
              width:150px !important;
          }
          .panel-default {
              background-color:#DFD1D1 !important;
          }
          
      </style>
  </head>

  <body>

      <form id="form1" runat="server">
    <div class="navbar navbar-default navbar-static-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
                <li><a href="Anasayfa.aspx" class="glyphicon glyphicon-tasks" target="_blank"> Anasayfa</a></li>
                <li><a href="hakkinda_yonetici.aspx" class="glyphicon glyphicon-briefcase" target="_blank"> Hakkında</a></li>
                <li><a href="iletisim_yonetici.aspx" class="glyphicon glyphicon-envelope" target="_blank"> İletişim</a></li>
                <li><a href="makaleEkle.aspx" class="glyphicon glyphicon-comment" target="_blank"> Makale Ekle</a></li>
                <li><a href="kategori_ekle.aspx" class="glyphicon glyphicon-credit-card" target="_blank"> Kategoriler</a></li>
                <li><a href="yorumOnayla.aspx" class="glyphicon glyphicon-eye-close" target="_blank"> Yorumlara Gözat</a></li>
                <li><a href="yonetici_ekle.aspx" class="glyphicon glyphicon-check" target="_blank"> Yönetici Hesabı</a></li>
                <li><a href="yonetici_guncelle.aspx" class="glyphicon glyphicon-cog" target="_blank"> Bilgileri Düzenle</a></li>
              <li><asp:Button ID="dologout" runat="server" Text="Çıkış Yap" CssClass="btn btn-link active" OnClick="dologout_Click" /></li>
          </ul>
        </div>
      </div>
    </div>


    <div class="container">
        <div class="alert alert-success">
            <div class="alert alert-warning">Bu alanda blog ile ilgili genel istatiksel durumu takip edebilirsiniz. Genel ayarlar için lütfen menüyü kullanın!</div>
           <div class="panel panel-default">
               <div class="panel panel-heading">
                   <h3 class="panel-title glyphicon-eye-open glyphicon"> &nbsp;Genel İstatistik Bilgileri</h3>
               </div>
               <div class="panel-body">
                  <ul class="list-group">
                      <li class="list-group-item list-group-item-info"><label class="label label-success istatistik pull-left"> Yayınlanmış Toplam Makale Sayısı</label> &nbsp; <label class="label label-danger istatistik2 pull-right"> <% Response.Write(Fonksiyonlar.toplamMakale()); %> </label></li>
                      <li class="list-group-item list-group-item-info"><label class="label label-success istatistik pull-left"> Yapılan Toplam Yorum Sayısı</label> &nbsp; <label class="label label-danger istatistik2 pull-right"> <% Response.Write(Fonksiyonlar.toplamYorum()); %> </label></li>
                      <li class="list-group-item list-group-item-info"><label class="label label-success istatistik pull-left"> Toplam Kategori Sayısı</label> &nbsp; <label class="label label-danger istatistik2 pull-right"> <% Response.Write(Fonksiyonlar.toplamKategori()); %> </label></li>
                  </ul>
               </div>               
           </div>
            <p></p>
              <div class="panel panel-default">
               <div class="panel panel-heading">
                   <h3 class="panel-title glyphicon-tasks glyphicon"> &nbsp;Son 5 Makale Ve Okunma Sayıları</h3>
               </div>
               <div class="panel-body">
                  <ul class="list-group">
                  <%
                        SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                        baglanti.Open();
                        SqlCommand komut = new SqlCommand();
                        komut.Connection = baglanti;
                        komut.CommandType = CommandType.Text;
                        komut.CommandText = "SELECT * FROM [makaleler] WHERE [makale_ID] > (SELECT MAX([makale_ID]) - 5 FROM [makaleler])";
                        DataTable tablo = new DataTable();
                        SqlDataAdapter adapt = new SqlDataAdapter(komut);
                        adapt.Fill(tablo);
                        foreach (DataRow bilgi in tablo.Rows)
                        {
                            if (!IsPostBack)
                            {
                                Response.Write("<li class='list-group-item list-group-item-info'><label class='label label-success istatistik pull-left'><a href='makaleDuzenle.aspx?ID="+bilgi["makale_ID"].ToString()+"'> " + bilgi["makale_basligi"].ToString() + "</a> </label> &nbsp; <label class='label label-danger istatistik2 pull-right'>" + bilgi["makale_hit"].ToString() + "  </label></li>");  
                            }
                        }
                        baglanti.Close();
                        baglanti.Dispose();          
                   %>                  
                  </ul>
               </div>               
           </div>
        </div>
      </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="Scripts/bootstrap.min.js"></script>
    <script src="Scripts/jquery-min-eski.js"></script>
      </form>
  </body>
</html>
