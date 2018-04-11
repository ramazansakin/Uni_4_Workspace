<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="makaleDetay.aspx.cs" Inherits="BlogPortalProjesi.makaleDetay" %>
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
    <meta name="author" content="Önder Coşkun, Ramazan Sakin, Mustafa Altınay "/>

    <title>Kategoriye Göre Listele</title>
    <!-- Bootstrap CSS dosyalarının dahil edilmesi. -->
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
          .container .jumbotron span.yorum {
               font-size:11px;
               margin-left:15px;
          }
          .container span.yorumcuadi {
               font-size:12px;
               margin-left:5px;
               color:#330000;
               font-weight:bolder;
          }
          .container span.yorumtarihi {
               font-size:12px;
               color:#330000;
               font-weight:bolder;
          }
          .container span.duzmetin {
               font-size:12px;
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
          #btn_yrm_gonder {
              margin-right:50px;
          }
          
      </style>
  </head>

  <body>
      <form id="form1" runat="server">
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigatione navigation</span>
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
            <li><a href="Iletisim.aspx">İletişim</a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
     <%
         string ID = Request.QueryString["ID"].ToString();
         SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
         baglanti.Open();
         SqlCommand komut = new SqlCommand();
         komut.Connection = baglanti;
         komut.CommandType = CommandType.Text;
         komut.CommandText = "select * from makaleler where makale_ID=" + ID;
         DataTable tablo = new DataTable();
         SqlDataAdapter adapt = new SqlDataAdapter(komut);
         adapt.Fill(tablo);
         
         // Kategoriye Göre Makalelerin Veritabanından Çekilmesi.
         foreach (DataRow item in tablo.Rows)
         {
             if (!IsPostBack)
             {
                 Response.Write("<div class=\"jumbotron\"><h1><a href=\"makaleDetay.aspx?ID=" + item["makale_ID"].ToString() + "\">" + item["makale_basligi"].ToString() + "</a></h1><span class=\"tarihveokunma\">Makale Tarihi:" + item["makale_tarihi"].ToString() + " -  Okunma Sayısı : " + item["makale_hit"].ToString() + "</span><p>" + item["makale_icerigi"].ToString() + "</p><span class=\"etiketler\">Etiketler: " + item["makale_etiketleri"].ToString() + "</span></div>");
             }
         }
         
         // Yorumların Veritabanından Çekilmesi.
         SqlCommand komutt = new SqlCommand();
         komutt.Connection = baglanti;
         komutt.CommandType = CommandType.Text;
         komutt.CommandText = "select * from yorumlar WHERE makale_ID=@ID order by yorum_ID DESC";
         komutt.Parameters.AddWithValue("@ID",ID);
         DataTable tablo2 = new DataTable();
         SqlDataAdapter adapt2 = new SqlDataAdapter(komutt);
         adapt2.Fill(tablo2);
         foreach (DataRow item in tablo2.Rows)
         {
             if (!IsPostBack)
             {
                 Response.Write("<span class=\"yorumcuadi\">" + item["yorumcu_adi"].ToString() + "</span><span class=\"duzmetin\"> isimli kullanıcı </span>" + "<span class=\"yorumtarihi\">" + item["yorum_tarihi"].ToString() + "" + "</span> <span class=\"duzmetin\">tarihinde yazdı : </span><div class=\"jumbotron\"><span class=\"yorum\">" + item["yorum_icerigi"].ToString() + "</span></h1></div>");
             }
         }
         
         // Makale Okunma Sayısının Düzenlenmesi.
         komut.CommandText = "UPDATE makaleler SET makale_hit=makale_hit+1 WHERE makale_ID=" + Request.QueryString["ID"].ToString();
         komut.ExecuteNonQuery();  
         // Bağlantının Kapatılması.     
         baglanti.Close();
         baglanti.Dispose();
     %>
            <table style="background-color: #EEEEEE; width: 100%">
            <tr>
                <td>
                    <asp:Label ID="lbl_yrmc_adi" CssClass="label label-default" runat="server" Text="Adı :"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txt_yrmcu_adi" runat="server" Width="90%"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lbl_yrmcu_eposta" runat="server" CssClass="label label-default"  Text="E-Posta :"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txt_yrmcu_eposta" runat="server" Width="90%"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lbl_yrmc_icerigi" CssClass="label label-default" runat="server" Text="Yorum İçeriği :"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txt_yrm_icerigi" runat="server" Width="90%"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style2" colspan="2">
                    <asp:Button ID="btn_yrm_gonder" CssClass="btn btn-default  btn-xs pull-right" runat="server" Text="Gönder" OnClick="btn_yrm_gonder_Click" />
                    
                    <asp:Literal ID="literalTextBos" runat="server"></asp:Literal>
                    
                    <input id="yrm_tmzl" type="reset" class="btn btn-default btn-xs pull-right" runat="server" value="Temizle" />    
                </td>
            </tr>
        </table>
              <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          </div>
          <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" ProviderName="<%$ ConnectionStrings:ConnectionString2.ProviderName %>"  SelectCommand="SELECT [kategori_adi], [kategori_ID] FROM [kategoriler]"></asp:SqlDataSource>
          <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
          <div class="list-group">
              <asp:DataList ID="DataList1" runat="server" DataSourceID="SqlDataSource1" DataKeyField="kategori_ID">
                  <ItemTemplate>                  
                       <a href="kategoriDetay.aspx?ID=<%# Eval("kategori_ID") %>" class="list-group-item"><span class="glyphicon glyphicon-hand-right"> <%# Eval("kategori_adi") %></span></a>
                  </ItemTemplate>
              </asp:DataList>
          </div>
        </div>
      </div>

      <hr/>

      <footer>
        <p>&copy; Blog Portal Web Projesi 2016</p>
      </footer>

    </div>
          <asp:HiddenField ID="HiddenField1" runat="server" />
    <script src="Scripts/jquery.min.js"></script>
    <script src="Scripts/bootstrap.min.js"></script>
    <script src="Scripts/offcanvas.js"></script>
      </form>
  </body>
</html>

