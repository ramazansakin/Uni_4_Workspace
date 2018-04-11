<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="yonetici_profil.aspx.cs" Inherits="BlogPortalProjesi.yonetici_profil" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Yönetici - Bilgileri Düzenle</title>
    <style>
        .btn {
          display: inline-block;
          padding: 6px 12px;
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
        fieldset {
            padding:10px;
            border:0px;
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
        .auto-style1 {
            height: 26px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <fieldset>
    <table width="100%" border="0">
  <tr>
    <td colspan="2">
        <asp:Label ID="Label1" runat="server" Font-Bold="True" Text="Yönetici Bilgileri Güncelleme Sayfası"></asp:Label>
      </td>
  </tr>
  <tr>
    <td class="auto-style1">
        <asp:Label ID="Label2" runat="server" Text="Kullanıcı Adı :"></asp:Label>
      </td>
    <td class="auto-style1">
        <asp:TextBox ID="kln_adi" runat="server" Width="90%"></asp:TextBox>
      </td>
  </tr>
  <tr>
    <td>
        <asp:Label ID="Label3" runat="server" Text="Şifre :"></asp:Label>
      </td>
    <td>
        <asp:TextBox ID="kln_sif" runat="server" Width="90%"></asp:TextBox>
      </td>
  </tr>
  <tr>
    <td colspan="2">
        <asp:Button ID="btn_guncelle" runat="server" Text="Bilgileri Güncelle" Width="100%" BackColor="#5CB85C" BorderColor="#4CAE4C" CssClass="btn" ForeColor="White" OnClick="btn_guncelle_Click" />
        <br />
        <br />
        <asp:Button ID="btn_yonetici_sil" runat="server" BackColor="#D9534F" BorderColor="#D43F3A" CssClass="btn" ForeColor="White" OnClick="btn_yonetici_sil_Click" Text="Yöneticiyi Sil" Width="100%" />
      </td>
  </tr>
  <tr>
    <td colspan="2">
        <asp:Label ID="lbl_ok" runat="server" Font-Bold="True" ForeColor="#003300" Text="Bilgiler başarıyla güncellendi!" Visible="False"></asp:Label>
        <br />
        <asp:Label ID="lbl_ok0" runat="server" Font-Bold="True" ForeColor="Maroon" Text="Bilgiler başarıyla silindi!" Visible="False"></asp:Label>
        <br />
        <asp:Label ID="lbl_error" runat="server" Font-Bold="True" ForeColor="Maroon" Text="HATA: Güncelleme işlemi yapılamadı." Visible="False"></asp:Label>
      </td>
  </tr>
</table>
</fieldset>
    </div>
    <hr id="alt" />
    <span id="altyazi">Yönetici - Bilgilerini Düzenleme Sayfası</span>
        <asp:SqlDataSource ID="sql_guncelle" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" OnSelecting="sql_guncelle_Selecting" SelectCommand="SELECT * FROM [hesaplar]" UpdateCommand="update hesaplar SET kullanici_adi=@kullanici_adi,kullanici_sifre=@kullanici_sifre where kullanici_ID=@ID">
            <UpdateParameters>
                <asp:ControlParameter ControlID="kln_adi" Name="kullanici_adi" PropertyName="Text"  />
                <asp:ControlParameter ControlID="kln_sif" Name="kullanici_sifre" PropertyName="Text" />
                <asp:QueryStringParameter Name="ID" QueryStringField="ID" />
            </UpdateParameters>
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="sql_yonetici_sil" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" ProviderName="<%$ ConnectionStrings:ConnectionString2.ProviderName %>" OnSelecting="sql_yonetici_sil_Selecting" DeleteCommand="DELETE FROM hesaplar WHERE kullanici_ID=@ID" SelectCommand="SELECT * FROM [hesaplar]" >
            <DeleteParameters>
                 <asp:QueryStringParameter Name="ID" QueryStringField="ID" />
            </DeleteParameters>
        </asp:SqlDataSource>
    </form>
    </body>
</html>
