                <h2>En Çok İzlenenler</h2>
                <div class="Content">
				<?php
				$sonuc = mysql_query("select * from articles order by views limit 0,5");
				if(mysql_num_rows($sonuc)!=0)
				{
					while($oku = mysql_fetch_array($sonuc,MYSQL_BOTH))
					{
						
                    echo '<div class="Article">';
                    echo '    <a href="Detail.php?ArticleId='.$oku['ArticleId'].'" class="clear">';
                    echo '        <img  onerror="this.onerror=null;this.src=\'images/default.png\';"  src="'.$oku['ImagePath'].'" alt="" />';
                    echo '        <span>'.$oku['Title'].'</span>';
                    echo '    </a>';
                    echo '</div>';
					}
				}else{
					echo "Kayıt bulunamadı";
				}
				?>
                </div>