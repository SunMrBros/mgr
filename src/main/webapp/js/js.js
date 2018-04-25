$(document).ready(function(){
	var sousuo = document.getElementById('nav_top_cont');
	var show_input = document.getElementsByTagName('input')[0];
	var p = document.getElementsByTagName('p')[4];
	var show_img = document.getElementsByTagName('img')[5];
	console.log(show_input)


	/*搜索框聚焦时*/
	show_input.onkeyup = function(){
		if(this.value != ''){
			this.style.width = '74%';
			p.style.display = 'block';
			show_img.style.display = 'block';
		}else{
			this.style.width = '84.6%';
			p.style.display = 'none';
			show_img.style.display = 'none';
		}
	}
	$('.del').click(function(){
		$('.input').val('');
		$('.input').css('width','84.6%');
		$('.p').css('display','none');
		$(this).css('display','none');
	})
})



function jidi(){window.location.href = "jidi.html"}
function jidi_xiangqing(url){window.location.href = url}
function xianlu(){window.location.href = "xianlu.html"}
function index(){window.location.href = "index.html"}
function jidi_tianjin(){window.location.href = "jidi_tianjin.html"}
function jidi_hebei(){window.location.href = "jidi_hebei.html"}
function yiriyou(){window.location.href = "yiriyou.html"}
function yiriyou_hebei(){window.location.href = "yiriyou_hebei.html"}
function duoriyou(){window.location.href = "duoriyou.html"}
function jingpinxianlu(){window.location.href = "jingpinxianlu.html"}
function yiriyou_beijing(){window.location.href = "yiriyou_beijing.html"}
function yiriyou_tianjin(){window.location.href = "yiriyou_tianjin.html"}
function duoriyou_xq(){window.location.href = "duoriyou_xq.html"}
function jingpin_xq(){window.location.href = "jingpin_xq.html"}
function fujin(){window.location.href = "fujin.html"}
function lunbo(){window.location.href = "lunbo.html"}
