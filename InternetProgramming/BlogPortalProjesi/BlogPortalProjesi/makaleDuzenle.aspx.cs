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
    public partial class makaleDuzenle : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null || Request.QueryString.Count <= 0)
            {
                Response.Redirect("Anasayfa.aspx");
            }
            else
            {
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "select * from makaleler where makale_ID=" + Request.QueryString["ID"].ToString();
                DataTable tablo = new DataTable();
                SqlDataAdapter adapt = new SqlDataAdapter(komut);
                adapt.Fill(tablo);
                foreach (DataRow item in tablo.Rows)
                {
                    if (!IsPostBack)
                    {
                        txt_kat_baslik.Text = item["makale_basligi"].ToString();
                        txt_mkl_icerigi.Value = item["makale_icerigi"].ToString();
                        txt_makale_etiket.Text = item["makale_etiketleri"].ToString();
                    }
                }
                baglanti.Close();
                baglanti.Dispose();
            }
        }

        protected void btn_makale_kydt_Click(object sender, EventArgs e)
        {
            if (txt_kat_baslik.Text != string.Empty && txt_makale_etiket.Text != string.Empty && txt_mkl_icerigi.Value != string.Empty) { 
            string makale_ozet = string.Empty;
            if (txt_mkl_icerigi.Value.Length > 500) {makale_ozet = txt_mkl_icerigi.Value.Substring(0, 497) + "...";}else{makale_ozet = txt_mkl_icerigi.Value;}

            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                try
                {   
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "UPDATE makaleler SET makale_basligi=@makale_basligi,makale_icerigi=@makale_icerigi,makale_etiketleri=@makale_etiketleri,makale_ozet=@makale_ozet WHERE makale_ID=@ID";
                    komut.Parameters.AddWithValue("@makale_basligi",txt_kat_baslik.Text);
                    komut.Parameters.AddWithValue("@makale_icerigi",txt_mkl_icerigi.Value);
                    komut.Parameters.AddWithValue("@makale_etiketleri",txt_makale_etiket.Text);
                    komut.Parameters.AddWithValue("@makale_ozet",makale_ozet);
                    komut.Parameters.AddWithValue("ID",Request.QueryString["ID"].ToString());
                    komut.ExecuteNonQuery();
                    Label5.Text = "Değişiklikler başarıyla kaydedildi.";
                    Label5.Visible = true;
                }
                catch (Exception ex)
                {
                    Response.Write(ex.Message);
                }
                finally
                {
                    baglanti.Close();
                    baglanti.Dispose();
                }
           }
        }
    }
}