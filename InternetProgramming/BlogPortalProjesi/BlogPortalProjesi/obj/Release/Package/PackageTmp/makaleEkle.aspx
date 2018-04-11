<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="makaleEkle.aspx.cs" Inherits="BlogPortalProjesi.makaleEkle" %>
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
        }

        textarea {
            height:250px;
        }
    </style>
    <!-- <script src="openEditor/ckeditor.js"></script> --> 
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Label ID="Label1" runat="server" Text="Yeni Makale Oluştur :" Font-Bold="True"></asp:Label>
        <br />
        <br />
        <fieldset>
        <table class="auto-style1">
            <tr>
                <td class="auto-style2">
                    <asp:Label ID="Label2" runat="server" Text="Kategori :" BackColor="White" Font-Bold="True"></asp:Label>
                </td>
                <td class="auto-style2">
                    <asp:DropDownList ID="kategoriTurleri" runat="server" CssClass="auto-style1" DataSourceID="SqlDataSource1" DataTextField="kategori_adi" DataValueField="kategori_adi" Height="32px" OnSelectedIndexChanged="kategoriTurleri_SelectedIndexChanged1">
                    </asp:DropDownList>
                </td>
            </tr>
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
                    <textarea id="txt_mkl_icerigi" runat="server" name="S1" class="auto-style1"></textarea></td>
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
                    <asp:Button ID="btn_makale_kydt" runat="server" OnClick="btn_makale_kydt_Click" Text="Makaleyi Kaydet"  ForeColor="#CCCCCC" Height="34px" BackColor="#333333" CssClass="auto-style1" />
                </td>
            </tr>
        </table>
       </fieldset>
    </div>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" SelectCommand="SELECT [kategori_adi] FROM [kategoriler]"></asp:SqlDataSource>
        <asp:Label ID="Label5" runat="server" Visible="False"></asp:Label>
    </form>
    <script>CKEDITOR.replace('txt_mkl_icerigi');</script>
</body>
</html>
