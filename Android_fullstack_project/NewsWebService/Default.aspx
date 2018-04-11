<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div style="text-align: right">
            <asp:Button ID="btnLogout" runat="server" Text="Çıkış Yap" OnClick="btnLogout_Click" />
        </div>

        <div style="text-align: center">
            <b>
                <asp:Label ID="lblInfo" runat="server" Visible="false"></asp:Label></b>
        </div>
        <div>
            <table style="width: 100%">
                <thead>
                    <tr>
                        <td>Başlık</td>
                        <td>Açıklama</td>
                        <td>Tarih</td>
                        <td>Kullanıcı Adı</td>
                        <td>Yayında mı?</td>
                        <td>Dosya Yolu</td>
                        <td>İşlemler</td>
                    </tr>
                </thead>
                <tbody>

                    <asp:Repeater ID="rptNews" runat="server" OnItemCommand="rptNews_ItemCommand">
                        <ItemTemplate>
                            <tr>
                                <td><%# Eval("Title") %></td>
                                <td><%# Eval("Description") %></td>
                                <td><%# Eval("DateTime") %></td>
                                <td><%# Eval("UserName") %></td>
                                <td><%# Eval("IsActive").ToString()== "True"? ("Evet"):("Hayır") %></td>
                                <td><%# Eval("FilePath") %></td>
                                <td>
                                    <asp:LinkButton ID="linkPublish" CommandArgument='<%#Eval("NewsId")+","+ Eval("IsActive")%>' CommandName="Publish" runat="server"><%# Eval("IsActive").ToString()== "True"? ("Yayından Kaldır"):("Yayınla") %></asp:LinkButton></td>
                            </tr>
                        </ItemTemplate>
                    </asp:Repeater>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>
