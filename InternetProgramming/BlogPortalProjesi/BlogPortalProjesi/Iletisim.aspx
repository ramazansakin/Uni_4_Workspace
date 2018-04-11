<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Iletisim.aspx.cs" Inherits="BlogPortalProjesi.Iletisim" %>
<%@ Import Namespace="System.Data" %>
<%@ Import Namespace="System.Data.SqlClient" %>  
<%@ Import Namespace="BlogPortalProjesi" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head runat="server">
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content="Önder Coşkun, Ramazan Sakin , Mustafa Altınay"/>

    <title>Iletişim</title>
    <link href="Css/bootstrap.css" rel="stylesheet"/>
    <link href="Css/offcanvas.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
      <style> 
          .container .jumbotron {
               padding:10px !important;
          }
          .container .jumbotron p {
               font-size:14px;
          }
          .container .jumbotron h1 {
               font-size:24px;
          }
          .container .jumbotron span.tarihveokunma {
               font-size:9px;
               margin-left:5px;
          }
          .container .jumbotron span.etiketler {
               font-size:9px;
               margin-left:5px;
          }
          a {
              color:#000000;
          }
          a:visited {
              color:#000000;
          }
          a:hover {
              color:#330000;
              text-decoration:none;
          }
          
      </style>
  </head>

  <body>
      <form id="form1" runat="server">
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="Anasayfa.aspx">Blog Portal</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="Anasayfa.aspx">Anasayfa</a></li>
            <li><a href="Hakkinda.aspx">Hakkında</a></li>
            <li class="active"><a href="Iletisim.aspx">İletişim</a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <div class="jumbotron">
                <%
                    SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                    try { 
                        baglanti.Open();
                        SqlCommand komut = new SqlCommand();
                        komut.Connection = baglanti;
                        komut.CommandType = CommandType.Text;
                        komut.CommandText = "select iletisim_baslik,iletisim_icerik from iletisim";
                        DataTable tablo = new DataTable();
                        SqlDataAdapter adapt = new SqlDataAdapter(komut);
                        adapt.Fill(tablo);
                        foreach (DataRow bilgi in tablo.Rows)
                        {
                            if (!IsPostBack)
                            {
                                Response.Write("<h1>" + bilgi["iletisim_baslik"].ToString() + "</h1>" + "<p>" + bilgi["iletisim_icerik"].ToString() + "</p>"); 
                            }
                        }
                    }
                    catch (Exception ex)
                    {
                        Response.Write("<font color=red>HATA:</font>" + ex.Message);
                    }
                    finally { 
                        baglanti.Close();
                        baglanti.Dispose();
                    } 
                %>
            </div>
              <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          </div>
          <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" ProviderName="<%$ ConnectionStrings:ConnectionString2.ProviderName  %>" SelectCommand="SELECT [kategori_adi], [kategori_ID] FROM [kategoriler]"></asp:SqlDataSource>
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
          <div class="list-group">
               <asp:DataList ID="DataList1" runat="server" DataSourceID="SqlDataSource1" DataKeyField="kategori_ID">
                  <ItemTemplate>
                    <a href="kategoriDetay.aspx?ID=<%# Eval("kategori_ID") %>" class="list-group-item"><span class="glyphicon glyphicon-hand-right"> <%# Eval("kategori_adi") %></span></a>
                  </ItemTemplate>
              </asp:DataList>
              <asp:SqlDataSource ID="SqlDataSource2" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" ProviderName="<%$ ConnectionStrings:ConnectionString2.ProviderName  %>" SelectCommand="SELECT * FROM [hakkinda]"></asp:SqlDataSource>
          </div>
        </div>
      </div>

      <hr/>

      <footer>
        <p>&copy; Blog Portal Web Projesi 2016</p>
      </footer>
    </div>
    <script src="Scripts/jquery.min.js"></script>
    <script src="Scripts/bootstrap.min.js"></script>
    <script src="Scripts/offcanvas.js"></script>
      </form>
  </body>
</html>