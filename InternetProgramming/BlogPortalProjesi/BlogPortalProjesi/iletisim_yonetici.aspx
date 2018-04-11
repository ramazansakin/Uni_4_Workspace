<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="iletisim_yonetici.aspx.cs" Inherits="BlogPortalProjesi.iletisim_yonetici" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">
        .auto-style1 {
            width: 100%;
        }
        fieldset {
            padding:20px;
            background-color:#FFFFFF;
        }

        textarea {
            height:250px;
        }
        body {
            background-color:#CECECE;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Label ID="Label1" runat="server" Text="İletişim Sayfasını Düzenle :" Font-Bold="True"></asp:Label>
        <br />
        <br />
        <fieldset>
        <table class="auto-style1">
           <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label3" runat="server" Text="Başlık :" BackColor="White" Font-Bold="True"></asp:Label>
                </td>
                <td class="auto-style3">
                    <asp:TextBox ID="iletisim_baslik" runat="server" CssClass="auto-style1" Height="30px"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="Label4" runat="server" Text="Metin :" BackColor="White" Font-Bold="True"></asp:Label>
                </td>
                <td>
                    <textarea id="iletisim_icerik" runat="server" name="S1" class="auto-style1"></textarea></td>
            </tr>
            <tr>
                <td colspan="2">
                    
                    <asp:Button ID="iletisim_guncelle" runat="server" Text="Güncelle!"  ForeColor="#CCCCCC" Height="34px" BackColor="#333333" CssClass="auto-style1" OnClick="Button1_Click" />
                    
                </td>
            </tr>
        </table>
       </fieldset>
    </div>
        <asp:Label ID="lbl_iletisim_guncelle" runat="server" Visible="False" Font-Bold="True" ForeColor="#009933"></asp:Label>
    </form>
</body>
</html>