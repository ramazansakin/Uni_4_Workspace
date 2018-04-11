using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.Sql;
using System.Data.SqlClient;


namespace BlogPortalProjesi
{
    public class Fonksiyonlar
    {
        
        // Anasayfa ve Kategori Görünümünde Karakter Sayısı Sınırlaması.
        public static string makaleyi_sinirla(string makale)
        {
            if (makale.Length > 500)
            {
                makale = makale.Substring(0, 497);
                makale += ("...");
                return makale;
            }
            return makale;
        }

        //  Toplam Makale Sayısı Metot
        public static string toplamMakale()
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            try
            {
                
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "Select * from makaleler";
                DataTable tablo = new DataTable();
                SqlDataAdapter adapt = new SqlDataAdapter(komut);
                adapt.Fill(tablo);
                string toplam_Makale_Sayisi = tablo.Rows.Count.ToString();
                return toplam_Makale_Sayisi;
            }

            catch (Exception ex)
            {
                return ex.Message;
            }

            finally
            {
                if (baglanti.State == ConnectionState.Open)
                {
                    baglanti.Close();
                }
            }
        }
        
        //  Toplam Yorum Sayısı Metot
        public static string toplamYorum()
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            try
            {
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "Select * from yorumlar";
                DataTable tablo = new DataTable();
                SqlDataAdapter adapt = new SqlDataAdapter(komut);
                adapt.Fill(tablo);
                string toplam_Yorum_Sayisi = tablo.Rows.Count.ToString();
                return toplam_Yorum_Sayisi;
            }

            catch (Exception ex)
            {
                return ex.Message;
            }

            finally
            {
                if (baglanti.State == ConnectionState.Open)
                {
                    baglanti.Close();
                }
            }
        }

        //  Toplam Kategori Sayısı Metot
        public static string toplamKategori()
        {
            SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
            try
            {
                baglanti.Open();
                SqlCommand komut = new SqlCommand();
                komut.Connection = baglanti;
                komut.CommandType = CommandType.Text;
                komut.CommandText = "Select * from kategoriler";
                DataTable tablo = new DataTable();
                SqlDataAdapter adapt = new SqlDataAdapter(komut);
                adapt.Fill(tablo);
                string toplam_kategori_Sayisi = tablo.Rows.Count.ToString();
                return toplam_kategori_Sayisi;
            }

            catch (Exception ex)
            {
                return ex.Message;
              
            }

            finally
            {
                if (baglanti.State == ConnectionState.Open)
                {
                    baglanti.Close();
                }
            }
        }
        
        // Veritabanı bağlantısı için ConnectionString tanımlaması.
        public static string connectionString()
        {
            return (@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\veritabani.mdf;Integrated Security=True;Connect Timeout=30");    
        }
    }
}