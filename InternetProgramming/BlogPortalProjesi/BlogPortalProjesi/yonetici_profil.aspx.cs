using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

namespace BlogPortalProjesi
{
    public partial class yonetici_profil : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null)
            {
                Response.Redirect("Anasayfa.aspx");
            }
            else { 
            if (Request.QueryString.Count <= 0)
            {
                Response.Write("<font color=red>QueryString Girilmedi!");
                Response.Redirect("Anasayfa.aspx");
            }
            else
            {
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "select * from hesaplar where kullanici_ID=" + Request.QueryString["ID"].ToString();
                DataTable tablo = new DataTable();
                SqlDataAdapter adapt = new SqlDataAdapter(komut);
                adapt.Fill(tablo);
                foreach (DataRow item in tablo.Rows)
                {
                    if (!IsPostBack)
                    {
                       kln_adi.Text = item["kullanici_adi"].ToString();
                       kln_sif.Text = item["kullanici_sifre"].ToString();
                    }
                }
                baglanti.Close();
                baglanti.Dispose();
            }
          }
        }

        protected void btn_guncelle_Click(object sender, EventArgs e)
        {
            try
            {
                sql_guncelle.Update();
                lbl_ok.Visible = true;
            }
            catch (Exception ex)
            {

                lbl_error.Visible = true;
            }
        }

        protected void sql_guncelle_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
        {
            try
            {
                sql_guncelle.Update();
                lbl_ok.Visible = true;
            }
            catch (Exception ex)
            {

                lbl_error.Visible = true;
            }
        }


        protected void btn_yonetici_sil_Click(object sender, EventArgs e)
        {
            try
            {
                sql_yonetici_sil.Delete();
                lbl_ok0.Visible = true;
            }
            catch (Exception ex)
            {

                lbl_error.Visible = true;
            }

            Response.Redirect("yonetici_guncelle.aspx");
        }

        protected void sql_yonetici_sil_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
        {
            try
            {
                sql_yonetici_sil.Delete();
                lbl_ok0.Visible = true;
            }
            catch (Exception ex)
            {

                lbl_error.Visible = true;
            }
            

        }

       
    }
}