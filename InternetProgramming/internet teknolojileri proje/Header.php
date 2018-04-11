<?php
	require_once "settings.php";
?>
    <div id="Header">
        <div class="Container">
            <!-- Logo ve reklam alanı -->
            <div class="Top clear">
                <div id="Logo">
                    <a href="/"><img src="images/Logo.png" alt="İngilizcece" title="ingilizcece" /></a>
                </div>
                <div id="Ads">
                    Reklam Alanı
					<!-- <img src="images/reklambanner.png" alt="İngilizcece" />  -->
                </div>
            </div>
            <!-- Menümüz -->
            <div id="Nav">
                <ul class="clear">
				<?php
				$sonuc = mysql_query("select * from categories");
				if(mysql_num_rows($sonuc)!=0)
				{
					while($oku = mysql_fetch_array($sonuc,MYSQL_BOTH))
					{
						echo '<li><a href="Category.php?categoryId='.$oku["Id"].'">'.$oku["Name"].'</a></li>';
					}
				}else{
					echo "<li><a href=\"#\">Kategori Bulunamadı</a></li>";
				}
				?>
                </ul>
            </div>
        </div>
    </div>