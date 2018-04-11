<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="yonetici_sil.aspx.cs" Inherits="BlogPortalProjesi.yonetici_sil" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:SqlDataSource ID="yoneticiSil" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" DeleteCommand="DELETE FROM hesaplar WHERE kullanici_ID=@ID" SelectCommand="SELECT * FROM [hesaplar]">
            <DeleteParameters>
                <asp:Parameter Name="ID" />
            </DeleteParameters>
        </asp:SqlDataSource>
    
    </div>
    </form>
</body>
</html>
