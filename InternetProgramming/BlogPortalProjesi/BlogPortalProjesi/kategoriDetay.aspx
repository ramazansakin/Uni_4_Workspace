<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="kategoriDetay.aspx.cs" Inherits="BlogPortalProjesi.kategoriDetay" %>
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
            <li class="active"><a href="Anasayfa.aspx">Anasayfa</a></li>
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
         komut.CommandText = "select * from makaleler where kategori_ID=@ID order by makale_ID DESC";
         komut.Parameters.AddWithValue("@ID",ID);
         DataTable tablo = new DataTable();
         SqlDataAdapter adapt = new SqlDataAdapter(komut);
         adapt.Fill(tablo);
         if (tablo.Rows.Count > 0) { 
             PagedDataSource pds = new PagedDataSource();
             pds.DataSource = tablo.DefaultView;
             pds.AllowPaging = true;
             pds.PageSize = 5;
             int currentPage;

             if (Request.QueryString["Sayfa"] != null)
             {
                 currentPage = Int32.Parse(Request.QueryString["Sayfa"]);
             }
             else
             {
                 currentPage = 1;
             }

             pds.CurrentPageIndex = currentPage - 1;
             Label1.Text = "Sayfa: " + currentPage + " / " + pds.PageCount;

             if (!pds.IsFirstPage)
             {
                 linkPrev.NavigateUrl = "kategoriDetay.aspx?Sayfa=" + (currentPage - 1) + "&ID=" + ID;
             }

             if (!pds.IsLastPage)
             {
                 linkNext.NavigateUrl = "kategoriDetay.aspx?Sayfa=" + (currentPage + 1) + "&ID=" + ID;
             }

             Repeater1.DataSource = pds;
             //Repeater1.DataBind();
             /* foreach (DataRow bilgi in tablo.Rows)
         {
             if (!IsPostBack)
             {
                 Response.Write("<div class=\"jumbotron\"><h1><a href=\"makaleDetay.aspx?ID="+bilgi["makale_ID"].ToString()+"\">" + bilgi["makale_basligi"].ToString() + "</a></h1><span class=\"tarihveokunma\">Makale Tarihi:" + bilgi["makale_tarihi"].ToString() + " -  Okunma Sayısı : " + bilgi["makale_hit"].ToString() + "</span><p>" + Fonksiyonlar.makaleyi_sinirla(bilgi["makale_icerigi"].ToString()) + "</p><span class=\"etiketler\">Etiketler: " + bilgi["makale_etiketleri"].ToString() + "</span></div>");
             }
         } 
          Repeater kullanıldığı için iptal edildi.
          */
         }
         else
         {
             linkNext.Visible = false;
             linkPrev.Visible = false;
             Label1.Visible = false;
             Response.Write("Bu kategoriye henüz haber eklenmedi!");
         }
         baglanti.Close();
         baglanti.Dispose();
     %>
             <asp:Repeater ID="Repeater1" runat="server" DataSourceID="SqlDataSource2">
                <ItemTemplate>
                    <div class="jumbotron">
                    <h1><a href="makaleDetay.aspx?ID=<%# Eval("makale_ID") %>"><%# Eval("makale_basligi") %></a></h1>
                    <span class="tarihveokunma">Makale Tarihi: <%# Eval("makale_tarihi") %>  -  Okunma Sayısı : <%# Eval("makale_hit") %></span>
                    <p><%# Eval("makale_ozet") %></p>
                    <span class="etiketler">Etiketler: <%# Eval("makale_etiketleri") %></span></div>
                </ItemTemplate>
          </asp:Repeater>
             <ul class="pager">
                <li class="previous"><asp:HyperLink ID="linkPrev" runat="server">Önceki Sayfa</asp:HyperLink></li>
                <li class="next"><asp:HyperLink ID="linkNext" runat="server">Sonraki Sayfa</asp:HyperLink></li>
                 <asp:SqlDataSource ID="SqlDataSource2" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" SelectCommand="SELECT * FROM [makaleler] WHERE ([kategori_ID] = @kategori_ID) ORDER BY [makale_tarihi] DESC">
                     <SelectParameters>
                         <asp:QueryStringParameter Name="kategori_ID" QueryStringField="ID" Type="Int32" />
                     </SelectParameters>
                 </asp:SqlDataSource>
            </ul>
            <asp:Label ID="Label1" runat="server" Text="" Font-Bold="True"></asp:Label>

            <!-- eski veriler -->
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

