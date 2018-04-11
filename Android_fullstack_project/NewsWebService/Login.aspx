<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Lütfen Giriş Yapınız</title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <table>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <asp:Label ID="lblInfo" runat="server" Visible="false"></asp:Label></td>
                </tr>
                <tr>
                    <td>Kullanıcı Adı: </td>
                    <td>
                        <asp:TextBox ID="txtUserName" runat="server"></asp:TextBox></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td>
                        <asp:TextBox ID="txtPassword" runat="server"></asp:TextBox></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <asp:Button ID="btnLogin" runat="server" OnClick="btnLogin_Click" Text="Giriş Yap" />
                    </td>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>
