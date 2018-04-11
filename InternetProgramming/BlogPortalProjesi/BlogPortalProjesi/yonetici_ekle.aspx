<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="yonetici_ekle.aspx.cs" Inherits="BlogPortalProjesi.yonetici_ekle" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">

        fieldset {
            padding:10px;
            border:0px;
        }
         .btn {
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
         #alt_yon_ekle {
            position:absolute;
            bottom:561px;
            width:100%;
            color:#303;
            left: 0px;
        }
         #altyazi_yon_ekle {
            position:absolute;
            bottom:2px;
            margin-left:10px;
            color:#666;
        }
         #txt_yon_sifre {
             width:90% !important;
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
        <asp:Label ID="Label1" runat="server" Font-Bold="True" Text="Yönetici Hesabı Oluşturma Sayfası"></asp:Label>
        <br />
        <br />
      </td>
  </tr>
  <tr>
    <td>
        <br />
        <asp:Label ID="Label2" runat="server" Text="Kullanıcı Adı :"></asp:Label>
      </td>
    <td>
        <br />
        <asp:TextBox ID="txt_yon_adi" runat="server" Width="90%"></asp:TextBox>
      </td>
  </tr>
  <tr>
    <td>
        <asp:Label ID="Label3" runat="server" Text="Şifre :"></asp:Label>
      </td>
    <td>
        
        <input id="txt_yon_sifre" type="password" width="500px" runat="server"/></td>
  </tr>
  <tr>
    <td colspan="2">
        <asp:Button ID="btn_ekle" runat="server" Text="Kaydet" Width="100%" BackColor="#5CB85C"  BorderColor="#4CAE4C" CssClass="btn" ForeColor="White" OnClick="btn_ekle_Click" />
      </td>
  </tr>
  <tr>
    <td colspan="2">
        <asp:Label ID="lbl_eklendi" runat="server" Font-Bold="True" ForeColor="#003300" Text="Yönetici hesabı başarıyla eklendi!" Visible="False"></asp:Label>
        <br />
        <asp:Label ID="lbl_eklenmedi" runat="server" Font-Bold="True" ForeColor="Maroon" Text="HATA: Yönetici hesabı oluşturulamadı." Visible="False"></asp:Label>
      </td>
  </tr>
</table>
</fieldset>
  </div>
        <asp:Literal ID="bosAlan" runat="server" Text="&lt;script language=&quot;javascript&quot;&gt;alert(&quot;HATA: Alanları boş geçemezsiniz!&quot;);&lt;/script&gt;"></asp:Literal>
    </form>
    <hr id="alt_yon_ekle" />
    <span id="altyazi_yon_ekle">Yönetici - Yeni Hesap Oluşturma Sayfası</span>   
</body>
</html>
