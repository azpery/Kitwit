var width;
var height;
var nbOfDoge = 0;
var dogeInProgress = false;
var interval;
var alreadyHided = false;
function refresh(){
	height =  $(window).height() ;
	width = $(window).width();
	if(!alreadyHided){
		setTimeout(function(){ $(".page-loader").css({"top":"-100%"}); }, 2300);
		$("#dogeMe").click(function(){
			if(!dogeInProgress){
				dogeInProgress = true;
				interval = setInterval(function(){
					generateDoge();
				}, 80);
			}else{
				dogeInProgress = false;
				clearInterval(interval);
			}
		})
		alreadyHided = true;
		return false;
	}else{
		return true
	}
};

function generateDoge(){
	var id = 'doge'+nbOfDoge;
	var doge = '<img src="img/unnamed.png" class="doge" id="'+id+'" style="top:'+generateRandom(height)+'px;">';
	nbOfDoge++;
	
	$("body").append(doge);
	
	setTimeout(function(){ $("#"+id).css({"top":generateRandom(height)+'px', "right":"-500px"}); }, 100);
	setTimeout(function(){ $("#"+id).remove();}, 2300);
	
}

function generateRandom(max){
	return Math.floor((Math.random() * max));
}

function initMoveAvatar(){
	var position = $("#fixed_avatar").position();
	$("#movable_avatar").css({"top":position.top+"px","left":position.left+"px"});
}

function moveAvatar(){
	var position = $("#target_avatar").offset();
	var pwith = $("#target_avatar").width();
	var pheight = $("#target_avatar").height();
	$("#movable_avatar").css({"top":position.top+"px","left":position.left+"px","height":pheight+"px","width":pwith+"px"});
	$("#movable_avatar").addClass("rotate");
	setTimeout(function(){
		setTimeout(function(){
			$("#movable_avatar").remove();
		}, 1000);
		$("#target_avatar").css({"opacity":"1"})
	}, 1000);
}