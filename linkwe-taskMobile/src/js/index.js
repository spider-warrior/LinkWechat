import '../css/base.css'
import '../css/index.css'
import {getPoster,getUserInfo,getToken,getWXUserInfo} from './api'
import {getUrlParam,getWxCode} from './utils'
import config from './contant'

$(function(){
    const taskFissionId = getUrlParam('fissionId');
    const eid = null
    //防止存入了错误的userinfo
    if(localStorage.getItem('userinfo') == undefined || localStorage.getItem('userinfo') == 'undefined'){
        localStorage.removeItem('userinfo')
    }
    $('.sharePic').click(function(){
        alert('长按图片在弹出菜单中发送给朋友或者可保存图片分享至朋友圈')
    });
    $('.myTaskDetail').click(function(){
        window.location.href = `./taskProcess.html?eid=${eid}&taskFissionId=${taskFissionId}`
    });
    let userinfo = localStorage.getItem('userinfo')
    //取缓存中的用户信息
    if(userinfo){
        try {
            userinfo = JSON.parse(userinfo)
            getPosterFlow({openId:userinfo.openId,lang:"zh_CN"})
        } catch (error) {
            alert(error)
        }
        return
    }
    //缓存中没有用户信息，进入授权流程
    getWxCode()
    const code = config.code
    if(!code){
        //防止跳转前进入流程
        return
    }
    try {
        getToken(code)
            .then(res=>{
                let data =res.data
                if(data && data.openId){
                    localStorage.setItem('userinfo',JSON.stringify(data))
                    getPosterFlow({openId:data.openId,lang:"zh_CN"})
                }
            })
       
    } catch (error) {
        console.log(error)
    }

    
})
//{openId:data.openId,lang:"zh_CN"}
function getPosterFlow(params){
    const fissionTargetId = getUrlParam('fissionTargetId');
    const posterId = getUrlParam('posterId');
    const taskFissionId = getUrlParam('fissionId');
    getWXUserInfo(params)
    .then(resp=>{
        let userData = resp.data;
        let unionId = userData.unionId
        getPoster({fissionTargetId,posterId,taskFissionId,unionId})
        .then(res=>{
            $('.posterImg').attr('src',res.data.posterUrl)
            localStorage.setItem('postersUrl',res.data.posterUrl)
        })
    })
}
