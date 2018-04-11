<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="kategori_ekle.aspx.cs" Inherits="BlogPortalProjesi.kategori_ekle" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Yönetici - Kategori Ekle</title>
    <style>
        #fieldset1 {
            padding:10px;
            position:absolute;
            left:5px;
            top:5px;
            border:1px;
        }
        #fieldset2 {
            padding:10px;
            margin-left:400px;
            position:absolute;
            top:5px;
            border:0px;
        }
        #fieldset3 {
            padding:10px;
            margin-left:800px;
            position:absolute;
            top:5px;
            border:0px;
        }
        .btnsil {
              display: inline-block;
              padding: 6px 12px;
              margin-bottom: 0;
              font-size: 14px;
              font-weight: normal;
              line-height: 1.42857143;
              text-align: center;
              white-space: nowrap;
              vertical-align: middle;
              cursor: pointer;
              -webkit-user-select: none;
                 -moz-user-select: none;
                  -ms-user-select: none;
                      user-select: none;
              background-image: none;
              border: 1px solid transparent;
              border-radius: 4px;
        }
        #alt {
            position:absolute;
            bottom:20px;
            width:100%;
            color:#303;
        }
        #altyazi {
            position:absolute;
            bottom:2px;
            margin-left:10px;
            color:#666;
        }
     
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <fieldset id="fieldset1">
        <table border="0">
            <tr width="100%">
                <td colspan="2">
                    <asp:Label ID="Label1" runat="server" Text="Kategori Ekle" Font-Bold="True"></asp:Label>
                </td>
            </tr>
            <tr width="100%">
                <td>
                    <asp:Label ID="Label2" runat="server" Text="Kategori Adı :"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="kategori_adi" runat="server"></asp:TextBox>
                </td>
            </tr>
               <tr>
                <td colspan="2">
                    <asp:Button ID="Button1" runat="server" Text="Kategori Ekle" Width="100%" BackColor="#5CB85C" BorderColor="#4CAE4C" CssClass="btnsil" ForeColor="White" OnClick="Button1_Click"/>
                </td>
            </tr>
            <tr>
                <td><asp:Label ID="Label6" runat="server" Text="Kategori eklendi!" Font-Bold="True" ForeColor="#003300" Visible="False"></asp:Label>
                </td>
            </tr>
        </table>
        <asp:Literal ID="Literal1" runat="server" Text="&lt;script language=&quot;javascript&quot;&gt;alert(&quot;HATA: Kategori Eklenemedi!&quot;);&lt;/script&gt;" Visible="False"></asp:Literal>
        <asp:Literal ID="Literal2" runat="server" Text="&lt;script language=&quot;javascript&quot;&gt;alert(&quot;HATA: Kategori adı belirtmediniz!&quot;);&lt;/script&gt;" Visible="False"></asp:Literal>
        </fieldset>
        <fieldset id="fieldset2">
        <table border="0">
            <tr width="100%">
                <td colspan="2">
                    <asp:Label ID="Label3" runat="server" Text="Kategori Sil" Font-Bold="True"></asp:Label>
                </td>
            </tr>
            <tr width="100%">
                <td>
                    <asp:Label ID="Label4" runat="server" Text="Kategori Adı :"></asp:Label>
                </td>
                <td>
                    <asp:DropDownList ID="kategori_list" runat="server" Width="153px" DataSourceID="SqlDataSource1" DataTextField="kategori_adi" DataValueField="kategori_adi" OnSelectedIndexChanged="kategori_list_SelectedIndexChanged">
                    </asp:DropDownList>
                </td>
            </tr>
               <tr>
                <td colspan="2">
                    <asp:Button ID="btn_kategori_sil" runat="server" Text="Kategori Sil" Width="100%" BackColor="#D9534F" BorderColor="#D43F3A" ForeColor="White" CssClass="btnsil" OnClick="btn_kategori_sil_Click"/>
                </td>
            </tr>
            <tr>
                <td><asp:Label ID="Label7" runat="server" Text="Kategori silindi!" Font-Bold="True" ForeColor="Maroon" Visible="False"></asp:Label></td>
            </tr>
        </table>
    </fieldset>

        <fieldset id="fieldset3">
        <table border="0">
            <tr width="100%">
                <td colspan="2">
                    <asp:Label ID="Label5" runat="server" Text="Kategori Seç :" Font-Bold="True"></asp:Label> <asp:DropDownList ID="kategori_guncelle_listesi" runat="server" Width="153px" DataSourceID="SqlDataSource1" DataTextField="kategori_adi" DataValueField="kategori_adi" AutoPostBack="True" OnSelectedIndexChanged="kategori_guncelle_listesi_SelectedIndexChanged">
                    </asp:DropDownList>
                </td>
            </tr>
            <tr width="100%">
                <td>
                    <asp:Label ID="Label8" runat="server" Text="Kategori Adı :"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="kategori_duzen" runat="server"></asp:TextBox>
                </td>
            </tr>
               <tr>
                <td colspan="2">
                    <asp:Button ID="btn_guncele_panpa" runat="server" Text="Güncelle" Width="100%" BackColor="#666ACF" BorderColor="#666ACF" CssClass="btnsil" ForeColor="White" OnClick="btn_guncele_panpa_Click"/>
                </td>
            </tr>
            <tr>
                <td><asp:Label ID="guncellendiApi" runat="server" Text="Kategori güncellendi!" Font-Bold="True" ForeColor="Maroon" Visible="False"></asp:Label></td>
            </tr>
        </table>
    </fieldset>
         
    </div>
    <hr id="alt" />
    <span id="altyazi">Yönetici - Kategori Ekleme Ve Çıkarma Sayfası</span>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" SelectCommand="SELECT [kategori_adi] FROM [kategoriler]"></asp:SqlDataSource>
        </form>
    </body>
</html>
