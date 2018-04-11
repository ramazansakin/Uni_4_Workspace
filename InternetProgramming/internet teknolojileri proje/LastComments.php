                <h2>En Son Yorumlar</h2>
                <div class="Content">
				<?php
				$sonuc = mysql_query("select * from comments order by CommentId desc limit 0,5");
				if(mysql_num_rows($sonuc)!=0)
				{
					while($oku = mysql_fetch_array($sonuc,MYSQL_BOTH))
					{
						
                    echo '<div class="Article">';
                    echo '    <a href="Detail.php?ArticleId='.$oku['ArticleId'].'" class="clear">';
                    echo '        <img  onerror="this.onerror=null;this.src=\'images/default.png\';"  src="http://1.bp.blogspot.com/-tCqsY0vq55c/Tm8qoLzqGYI/AAAAAAAAAfM/6XCkFTJ1CeA/s1600/Chat-icon.png" alt="" />';
                    echo '        <span>'.$oku['Comment'].'</span>';
                    echo '    </a>';
                    echo '</div>';
					}
				}else{
					echo "Kayıt bulunamadı";
				}
				?>
                </div>
				