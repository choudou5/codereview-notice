/**
 * 成功提示
 * @param msg
 * @param timeOut
 */
function noticeSuccess(msg, timeOut){
	if(!timeOut) timeOut = 3000;
	new jBox('Notice', {
    	id: "jBoxSuccessMsgNotice",
        content: msg,
        autoClose: timeOut,
        color: 'green',
        attributes: {
            x: 'right',
            y: 'top'
        },
    });
}

/**
 * 失败提示
 * @param msg
 * @param timeOut
 */
function noticeError(msg, timeOut){
	if(!timeOut) timeOut = 3000;
	new jBox('Notice', {
    	id: "jBoxErrorMsgNotice",
        content: msg,
        autoClose: timeOut,
        color: 'red',
        attributes: {
        	x: 'right',
            y: 'top'
        },
    });
}


var noticeObj;
function notice(msg, timeOut, color){
	if(!timeOut) timeOut = 12000;
	if(!color) color = 'black';
	
	noticeObj = new jBox('Notice', {
    	id: "jBoxMsgNotice",
        content: msg,
        autoClose: timeOut,
        color: color,
        attributes: {
        	x: 'right',
            y: 'top'
        },
    });
}

function noticeClose(delayTime){
	if(delayTime != null){
		setTimeout(function(){
			if(noticeObj != null){
				noticeObj.destroy();
				noticeObj = null;
			}
		}, delayTime);
	}else{
		if(noticeObj != null){
			noticeObj.destroy();
			noticeObj = null;
		}
	}
	
}


var LogUtil = {
	info: function (message) {
		console.log("info:"+message);
	},
	error: function (message, e) {
		console.log("error:"+message+", e:"+e);
	}
};

