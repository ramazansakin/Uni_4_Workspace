<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="makaleDuzenle.aspx.cs" Inherits="BlogPortalProjesi.makaleDuzenle" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">
        a {
            color:#300000;
            text-decoration:none;
        }
        a:hover {
            text-decoration:underline;
        }
        a:visited {
            color:#300000;
        }
        fieldset {
            padding:20px;
        }

        .auto-style1 {
            width: 100%;
        }
        
        textarea {
            height:250px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Label ID="Label1" runat="server" Text="Makaleyi Düzenle :" Font-Bold="True"></asp:Label>
        <br />
        <br />
        <fieldset>
        <table class="auto-style1">
            <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label3" runat="server" Text="Başlık :" BackColor="White" Font-Bold="True"></asp:Label>
                </td>
                <td class="auto-style3">
                    <asp:TextBox ID="txt_kat_baslik" runat="server" class="auto-style1" Height="30px"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="Label4" runat="server" Text="Makale :" BackColor="White" Font-Bold="True"></asp:Label>
                </td>
                <td>
                    <textarea id="txt_mkl_icerigi" runat="server" name="S1" class="auto-style1" cols="20" rows="1"></textarea></td>
            </tr>
             <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label6" runat="server" Text="Etiketler :" BackColor="White" Font-Bold="True"></asp:Label>
                </td>
                <td class="auto-style3">
                    <asp:TextBox ID="txt_makale_etiket" runat="server" class="auto-style1" Height="30px"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <asp:Button ID="btn_makale_kydt" runat="server" Text="Güncelle!"  ForeColor="#CCCCCC" Height="34px" BackColor="#333333" CssClass="auto-style1" OnClick="btn_makale_kydt_Click" />
                </td>
            </tr>
        </table>
       </fieldset>
    </div>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" ProviderName="<%$ ConnectionStrings:ConnectionString2.ProviderName %>" SelectCommand="SELECT [kategori_adi] FROM [kategoriler]"></asp:SqlDataSource>
        <asp:Label ID="Label5" runat="server" Visible="False" ForeColor="#006600"></asp:Label>
        <br />
        <a href="makaleSil.aspx?ID=<% Response.Write(Request.QueryString["ID"].ToString()); %>">Bu makaleyi silmek için tıklayın.</a>
    </form>
</body>
</html>
