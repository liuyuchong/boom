(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1aff394c"],{"64b7":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("d2-container",[a("template",{slot:"header"},[a("div",{staticClass:"module-header"},[a("div",{staticClass:"left-box"},[a("el-form",{attrs:{size:"mini",inline:""}},[a("el-form-item",[a("el-select",{attrs:{placeholder:"检索字段"},on:{change:function(t){return e.handleSearchSelect(t)}},model:{value:e.query.queryKey,callback:function(t){e.$set(e.query,"queryKey",t)},expression:"query.queryKey"}},e._l(e.searchOptions,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value}})})),1)],1),a("el-form-item",["fixCode"===this.query.queryKey||"childCode"===this.query.queryKey?a("el-input",{attrs:{placeholder:"检索码"},model:{value:e.query.queryValue,callback:function(t){e.$set(e.query,"queryValue",t)},expression:"query.queryValue"}}):"status"===this.query.queryKey?a("el-select",{attrs:{placeholder:"请选择状态"},model:{value:e.query.queryValue,callback:function(t){e.$set(e.query,"queryValue",t)},expression:"query.queryValue"}},e._l(e.statusOptions,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1):"fromTo"===this.query.queryKey?a("div",{staticClass:"during-box"},[a("el-input",{attrs:{width:"50",placeholder:"起始子码"},model:{value:e.query.queryValue,callback:function(t){e.$set(e.query,"queryValue",t)},expression:"query.queryValue"}}),a("el-input",{attrs:{placeholder:"结束子码"},model:{value:e.query.queryValue2,callback:function(t){e.$set(e.query,"queryValue2",t)},expression:"query.queryValue2"}})],1):e._e()],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1),a("div",{staticClass:"right-box"},[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.handleAdd}},[e._v("批量操作")])],1)])]),a("d2-module",[a("div",{staticClass:"table-box"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:"",height:"500"}},[a("el-table-column",{attrs:{fixed:"",prop:"id",label:"id",width:"100"}}),a("el-table-column",{attrs:{prop:"fixCode",label:"fixCode",width:"100"}}),a("el-table-column",{attrs:{prop:"childCode",label:"childCode",width:"100"}}),a("el-table-column",{attrs:{prop:"displayStoreTime",label:"storeTime",width:"160"}}),a("el-table-column",{attrs:{prop:"displaySendTime",label:"sendTime",width:"160"}}),a("el-table-column",{attrs:{prop:"displayBackTime",label:"backTime",width:"160"}}),a("el-table-column",{attrs:{prop:"keeper",label:"keeper",width:"100"}}),a("el-table-column",{attrs:{prop:"consumer",label:"consumer",width:"100"}}),a("el-table-column",{attrs:{prop:"status",label:"status",width:"100"}}),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("删除")])]}}])})],1),a("d2-pagination",{attrs:{marginTop:"",currentPage:e.pager.pageNo,pageSize:e.pager.pageSize,total:e.pager.total},on:{doCurrentChange:e.handleCurrentChange}})],1)]),a("el-dialog",{attrs:{title:e.editTitle,visible:e.dialogFormVisible,width:"700px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.form,"label-position":"right"}},[a("el-form-item",{attrs:{label:"optType","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.optType,callback:function(t){e.$set(e.form,"optType",t)},expression:"form.optType"}},e._l(e.statusOptions,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value}})})),1)],1),a("el-form-item",{attrs:{label:"固定码","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.fixCode,callback:function(t){e.$set(e.form,"fixCode",t)},expression:"form.fixCode"}})],1),a("el-form-item",{attrs:{label:"发码起始值","label-width":e.formLabelWidth}},[a("el-input",{attrs:{disabled:e.fromToDisable,autocomplete:"off"},model:{value:e.form.from,callback:function(t){e.$set(e.form,"from",t)},expression:"form.from"}})],1),a("el-form-item",{attrs:{label:"发码终止值","label-width":e.formLabelWidth}},[a("el-input",{attrs:{disabled:e.fromToDisable,autocomplete:"off"},model:{value:e.form.to,callback:function(t){e.$set(e.form,"to",t)},expression:"form.to"}})],1),a("el-form-item",{attrs:{label:"保存者","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.keeper,callback:function(t){e.$set(e.form,"keeper",t)},expression:"form.keeper"}})],1),a("el-form-item",{attrs:{label:"领退者","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.consumer,callback:function(t){e.$set(e.form,"consumer",t)},expression:"form.consumer"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmit}},[e._v("确 定")])],1)],1)],2)},o=[],l=(a("d81d"),a("d3b7"),a("25f0"),a("96cf"),a("1da1")),i=a("9bd2"),s=a("c276"),n=a("365c"),u={name:"page1",data:function(){return{query:{queryKey:"fixCode",queryValue:"",queryValue2:""},formLabelWidth:"120px",pager:{pageNo:1,pageSize:10,total:10},searchOptions:[{label:"固定码",value:"fixCode"},{label:"子码",value:"childCode"},{label:"状态",value:"status"},{label:"子码区间",value:"fromTo"}],statusOptions:[{label:"入库",value:1},{label:"发出",value:2},{label:"退回",value:3},{label:"已使用",value:4}],tableData:[],form:{date:"",optType:1,fixCode:"",from:"",to:"",keeper:"",consumer:""},isCreating:!1,dialogFormVisible:!1,fromToDisable:!1}},computed:{editTitle:function(){return this.isCreating?"新增":"编辑信息"}},created:function(){this.getList()},methods:{getList:function(){var e=this;return Object(l["a"])(regeneratorRuntime.mark((function t(){var a,r;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return a={},"fromTo"===e.query.queryKey?(a.from=e.query.queryValue,a.to=e.query.queryValue2):a[e.query.queryKey]=e.query.queryValue,a.pageNo=e.pager.pageNo,a.pageSize=e.pager.pageSize,t.next=6,n["a"].DETONATOR_LIST(a);case 6:r=t.sent,r&&(e.pager.total=r.total,e.tableData=e.fixData(r.leiGuanList));case 8:case"end":return t.stop()}}),t)})))()},fixData:function(e){return 0===e.length?[]:e.map((function(e){return e.displayStoreTime=s["a"].timeFormat(e.storeTime),e.displaySendTime=s["a"].timeFormat(e.sendTime),e.displayBackTime=s["a"].timeFormat(e.backTime),e}))},handleSearchSelect:function(e){this.query.queryValue="",this.query.queryValue2=""},handleCurrentChange:function(e){e&&(this.pager.pageNo=e),this.getList()},handleAdd:function(){this.isCreating=!0,this.fromToDisable=!1,this.form={optType:1,from:"",to:"",fixCode:"",keeper:"",consumer:""},this.dialogFormVisible=!0},handleEdit:function(e){this.isCreating=!1;var t=Object.assign({},e);t.from=t.childCode,t.to=t.childCode,this.fromToDisable=!0,this.form=Object.assign(this.form,t),this.dialogFormVisible=!0,console.log(this.fromToDisable)},handleDelete:function(e){var t=this,a=e.id,r=[];r=a.constructor!==Array?[a]:Object.assign([],a),this.$confirm("此操作将永久删除id为".concat(a.toString(),"的记录, 是否继续?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(i["a"])({url:"/machine/del",method:"post",data:{ids:r}}).then((function(e){t.$message.success("删除成功"),t.getList()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleSubmit:function(){this.dialogFormVisible=!1,console.log(this.form)}}},c=u,m=(a("b1ed"),a("2877")),d=Object(m["a"])(c,r,o,!1,null,"f1528764",null);t["default"]=d.exports},"9bd2":function(e,t,a){"use strict";a("99af"),a("d3b7");var r=a("4360"),o=a("cebe"),l=a.n(o),i=a("5f72"),s=a("c276"),n=Object({NODE_ENV:"production",VUE_APP_TITLE:"D2Admin",VUE_APP_API:"/api/",VUE_APP_REPO:"https://github.com/d2-projects/d2-admin-start-kit",VUE_APP_I18N_LOCALE:"zh-chs",VUE_APP_I18N_FALLBACK_LOCALE:"en",VUE_APP_ELEMENT_COLOR:"#409EFF",VUE_APP_VERSION:"1.20.1",VUE_APP_BUILD_TIME:"2020-11-1 22:42:46",BASE_URL:"/"}).VUE_APP_CAS_URL;function u(e){var t=new Error(e);throw c(t),t}function c(e){r["a"].dispatch("d2admin/log/add",{type:"error",err:e,info:"数据请求异常"}),"timeout of 5000ms exceeded"===e.message&&(e.message="请求超时"),Object(i["Message"])({message:e.message,type:"error",duration:5e3})}var m=l.a.create({baseURL:"/api/",timeout:1e4});m.interceptors.request.use((function(e){if(!/^https:\/\/|http:\/\//.test(e.url)){var t=s["a"].cookies.get("token");t&&"undefined"!==t&&(e.headers["X-Token"]=t)}return/^\/open\//.test(e.url)&&(e.baseURL="/"),e}),(function(e){console.log(e),Promise.reject(e)})),m.interceptors.response.use((function(e){var t=e.data,a=t.code;if(void 0===a)return t;switch(a){case 0:return t.data;case"xxx":u("".concat(e.config.url,": [ code: xxx ] ").concat(t.msg));break;default:u("".concat(e.config.url,": ").concat(t.message));break}}),(function(e){if(e&&e.response)switch(e.response.status){case 400:e.message="请求错误";break;case 401:window.location.href=e.response.message||"".concat(n,"/login?service=").concat(encodeURIComponent(window.location.href)),e.message="未授权，请登录";break;case 403:e.message="权限不够，如需调整请联系架构组同学";break;case 404:e.message="请求地址出错: ".concat(e.response.config.url);break;case 408:e.message="请求超时";break;case 500:e.message="服务器内部错误";break;case 501:e.message="服务未实现";break;case 502:e.message="网关错误";break;case 503:e.message="服务不可用";break;case 504:e.message="网关超时";break;case 505:e.message="HTTP版本不受支持";break;default:break}return c(e),Promise.reject(e)})),t["a"]=m},"9c31":function(e,t,a){},b1ed:function(e,t,a){"use strict";var r=a("9c31"),o=a.n(r);o.a}}]);