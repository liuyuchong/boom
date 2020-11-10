(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b6d559c0"],{"64b7":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("d2-container",[a("template",{slot:"header"},[a("div",{staticClass:"module-header"},[a("div",{staticClass:"left-box"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,size:"mini",model:e.formInline}},[a("el-form-item",{attrs:{label:"固定码"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.formInline.fixCode,callback:function(t){e.$set(e.formInline,"fixCode",t)},expression:"formInline.fixCode"}})],1),a("el-form-item",{attrs:{label:"子码范围"}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{placeholder:""},model:{value:e.formInline.from,callback:function(t){e.$set(e.formInline,"from",t)},expression:"formInline.from"}})],1),a("el-col",{staticClass:"ta-c",attrs:{span:4}},[e._v("-")]),a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{placeholder:""},model:{value:e.formInline.to,callback:function(t){e.$set(e.formInline,"to",t)},expression:"formInline.to"}})],1)],1)],1),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{attrs:{clearable:"",placeholder:"状态"},model:{value:e.formInline.status,callback:function(t){e.$set(e.formInline,"status",t)},expression:"formInline.status"}},e._l(e.statusOptions,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value}})})),1)],1),a("el-form-item",{attrs:{label:"保管人"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.formInline.keeper,callback:function(t){e.$set(e.formInline,"keeper",t)},expression:"formInline.keeper"}})],1),a("el-form-item",{attrs:{label:"领退人"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.formInline.consumer,callback:function(t){e.$set(e.formInline,"consumer",t)},expression:"formInline.consumer"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.hadndleSearch}},[e._v("查询")])],1)],1)],1),a("div",{staticClass:"right-box"},[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.handleAdd}},[e._v("批量操作")])],1)])]),a("d2-module",[a("div",{staticClass:"table-box"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:"",height:"500"}},[a("el-table-column",{attrs:{fixed:"",prop:"id",label:"id",width:"100"}}),a("el-table-column",{attrs:{label:"固定码",prop:"fixCode",width:"100"}}),a("el-table-column",{attrs:{prop:"childCode",label:"子码",width:"100"}}),a("el-table-column",{attrs:{prop:"displayStoreTime",label:"入库时间",width:"160"}}),a("el-table-column",{attrs:{prop:"displaySendTime",label:"发出时间",width:"160"}}),a("el-table-column",{attrs:{prop:"displayBackTime",label:"归还时间",width:"160"}}),a("el-table-column",{attrs:{prop:"keeper",label:"保管人",width:"100"}}),a("el-table-column",{attrs:{prop:"consumer",label:"领退人",width:"100"}}),a("el-table-column",{attrs:{prop:"status",label:"状态",clearable:"",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.status?a("el-tag",{attrs:{type:"success"}},[e._v(" 入库 ")]):e._e(),2===t.row.status?a("el-tag",{attrs:{type:"danger"}},[e._v(" 发出 ")]):e._e(),3===t.row.status?a("el-tag",{attrs:{type:"info"}},[e._v(" 退还 ")]):e._e(),4===t.row.status?a("el-tag",{attrs:{type:"warning"}},[e._v(" 已使用 ")]):e._e()]}}])}),a("el-table-column",{attrs:{fixed:"right",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("编辑")])]}}])})],1),a("el-pagination",{attrs:{"current-page":e.pager.pageNo,"page-size":10,layout:"total, prev, pager, next",total:e.pager.total},on:{"update:currentPage":function(t){return e.$set(e.pager,"pageNo",t)},"update:current-page":function(t){return e.$set(e.pager,"pageNo",t)},"current-change":e.handleCurrentChange}})],1)]),a("el-dialog",{attrs:{title:"批量操作",visible:e.dialogBatchFormVisible,width:"700px"},on:{"update:visible":function(t){e.dialogBatchFormVisible=t}}},[a("el-form",{attrs:{model:e.batchForm,size:"mini","label-position":"right"}},[a("el-form-item",{attrs:{label:"日期","label-width":e.formLabelWidth}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.batchForm.date,callback:function(t){e.$set(e.batchForm,"date",t)},expression:"batchForm.date"}})],1),a("el-form-item",{attrs:{label:"操作类型","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.batchForm.optType,callback:function(t){e.$set(e.batchForm,"optType",t)},expression:"batchForm.optType"}},e._l(e.statusOptions,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value}})})),1)],1),a("el-form-item",{attrs:{label:"固定码","label-width":e.formLabelWidth}},[a("el-col",{attrs:{span:10}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.fixCode,callback:function(t){e.$set(e.batchForm,"fixCode",t)},expression:"batchForm.fixCode"}})],1)],1),a("el-form-item",{attrs:{label:"发码1","label-width":e.formLabelWidth}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.from1,callback:function(t){e.$set(e.batchForm,"from1",t)},expression:"batchForm.from1"}})],1),a("el-col",{staticClass:"ta-c",attrs:{span:2}},[e._v("-")]),a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.to1,callback:function(t){e.$set(e.batchForm,"to1",t)},expression:"batchForm.to1"}})],1)],1)],1),a("el-form-item",{attrs:{label:"发码2","label-width":e.formLabelWidth}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.from2,callback:function(t){e.$set(e.batchForm,"from2",t)},expression:"batchForm.from2"}})],1),a("el-col",{staticClass:"ta-c",attrs:{span:2}},[e._v("-")]),a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.to2,callback:function(t){e.$set(e.batchForm,"to2",t)},expression:"batchForm.to2"}})],1)],1)],1),a("el-form-item",{attrs:{label:"发码3","label-width":e.formLabelWidth}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.from3,callback:function(t){e.$set(e.batchForm,"from3",t)},expression:"batchForm.from3"}})],1),a("el-col",{staticClass:"ta-c",attrs:{span:2}},[e._v("-")]),a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.to3,callback:function(t){e.$set(e.batchForm,"to3",t)},expression:"batchForm.to3"}})],1)],1)],1),a("el-form-item",{attrs:{label:"保管人","label-width":e.formLabelWidth}},[a("el-col",{attrs:{span:10}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.keeper,callback:function(t){e.$set(e.batchForm,"keeper",t)},expression:"batchForm.keeper"}})],1)],1),a("el-form-item",{attrs:{label:"领退人","label-width":e.formLabelWidth}},[a("el-col",{attrs:{span:10}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.batchForm.consumer,callback:function(t){e.$set(e.batchForm,"consumer",t)},expression:"batchForm.consumer"}})],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogBatchFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleBatchSubmit}},[e._v("确 定")])],1)],1),a("el-dialog",{attrs:{title:e.editTitle,visible:e.dialogFormVisible,width:"700px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.editForm,size:"mini","label-position":"right"}},[a("el-form-item",{attrs:{label:"固定码","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.fixCode,callback:function(t){e.$set(e.editForm,"fixCode",t)},expression:"editForm.fixCode"}})],1),a("el-form-item",{attrs:{label:"发码","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.childCode,callback:function(t){e.$set(e.editForm,"childCode",t)},expression:"editForm.childCode"}})],1),a("el-form-item",{attrs:{label:"入库时间","label-width":e.formLabelWidth}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.editForm.storeTime,callback:function(t){e.$set(e.editForm,"storeTime",t)},expression:"editForm.storeTime"}})],1),a("el-form-item",{attrs:{label:"发出时间","label-width":e.formLabelWidth}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.editForm.sendTime,callback:function(t){e.$set(e.editForm,"sendTime",t)},expression:"editForm.sendTime"}})],1),a("el-form-item",{attrs:{label:"退回时间","label-width":e.formLabelWidth}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.editForm.backTime,callback:function(t){e.$set(e.editForm,"backTime",t)},expression:"editForm.backTime"}})],1),a("el-form-item",{attrs:{label:"使用时间","label-width":e.formLabelWidth}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.editForm.useTime,callback:function(t){e.$set(e.editForm,"useTime",t)},expression:"editForm.useTime"}})],1),a("el-form-item",{attrs:{label:"保管人","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.keeper,callback:function(t){e.$set(e.editForm,"keeper",t)},expression:"editForm.keeper"}})],1),a("el-form-item",{attrs:{label:"领退人","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.editForm.consumer,callback:function(t){e.$set(e.editForm,"consumer",t)},expression:"editForm.consumer"}})],1),a("el-form-item",{attrs:{label:"状态",clearable:"","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.editForm.status,callback:function(t){e.$set(e.editForm,"status",t)},expression:"editForm.status"}},e._l(e.statusOptions,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value}})})),1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmit}},[e._v("确 定")])],1)],1)],2)},l=[],r=(a("d81d"),a("d3b7"),a("25f0"),a("96cf"),a("1da1")),i=a("9bd2"),s=a("c276"),n=a("365c"),c={name:"page1",data:function(){return{query:{queryKey:"fixCode",queryValue:"",queryValue2:""},formInline:{fixCode:"",childCode:"",from:"",to:"",status:"",keepe:"",consumer:""},batchForm:{date:"",optType:"",fixCode:"",from1:"",to1:"",from2:"",to2:"",from3:"",to3:"",keeper:"",consumer:""},formLabelWidth:"120px",pager:{pageNo:1,pageSize:10,total:10},searchOptions:[{label:"固定码",value:"fixCode"},{label:"子码",value:"childCode"},{label:"状态",value:"status"},{label:"子码区间",value:"fromTo"}],types:[{label:"0.5kg/柱",value:0},{label:"1kg/柱",value:1},{label:"2kg/柱",value:2}],statusOptions:[{label:"入库",value:1},{label:"发出",value:2},{label:"退回",value:3}],tableData:[],editForm:{id:"",fixCode:0,childCode:0,storeTime:"",sendTime:"",backTime:"",useTime:"",status:"",keeper:"",consumer:""},isCreating:!1,dialogFormVisible:!1,dialogBatchFormVisible:!1,pickerOptions:{shortcuts:[{text:"今天",onClick:function(e){e.$emit("pick",new Date)}},{text:"昨天",onClick:function(e){var t=new Date;t.setTime(t.getTime()-864e5),e.$emit("pick",t)}},{text:"一周前",onClick:function(e){var t=new Date;t.setTime(t.getTime()-6048e5),e.$emit("pick",t)}}]}}},computed:{editTitle:function(){return this.isCreating?"新增":"编辑信息"}},created:function(){this.getList()},methods:{getList:function(){var e=this;return Object(r["a"])(regeneratorRuntime.mark((function t(){var a,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return a={},a=Object.assign({},e.formInline),a.pageNo=e.pager.pageNo,a.pageSize=e.pager.pageSize,t.next=6,n["a"].DETONATOR_LIST(a);case 6:o=t.sent,o&&(e.pager.total=o.total,e.tableData=e.fixData(o.leiGuanList));case 8:case"end":return t.stop()}}),t)})))()},fixData:function(e){return e&&0!==e.length?e.map((function(e){return e.displayStoreTime=s["a"].dateFormat(e.storeTime),e.displaySendTime=s["a"].dateFormat(e.sendTime),e.displayBackTime=s["a"].dateFormat(e.backTime),e.displayUseTime=s["a"].dateFormat(e.useTime),e})):[]},hadndleSearch:function(){this.getList()},handleCurrentChange:function(e){e&&(this.pager.pageNo=e),this.getList()},handleAdd:function(){this.isCreating=!0,this.batchForm={optType:"",batchNum:"",boxFrom:"",boxTo:"",type:"",colFrom:"",colTo:"",keeper:"",consumer:""},this.dialogBatchFormVisible=!0},handleEdit:function(e){this.isCreating=!1;var t=Object.assign({},e);this.form=Object.assign(this.editForm,t),this.dialogFormVisible=!0},handleDelete:function(e){var t=this,a=e.id,o=[];o=a.constructor!==Array?[a]:Object.assign([],a),this.$confirm("此操作将永久删除id为".concat(a.toString(),"的记录, 是否继续?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(i["a"])({url:"/machine/del",method:"post",data:{ids:o}}).then((function(e){t.$message.success("删除成功"),t.getList()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleSubmit:function(){var e=this;return Object(r["a"])(regeneratorRuntime.mark((function t(){var a,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return a=Object.assign({},e.editForm),t.next=3,n["a"].DETONATOR_UPDATE(a);case 3:o=t.sent,o&&o>=0?(e.$message.success("操作成功"),e.dialogFormVisible=!1):e.$message.error("操作失败");case 5:case"end":return t.stop()}}),t)})))()},handleBatchSubmit:function(){var e=this;return Object(r["a"])(regeneratorRuntime.mark((function t(){var a,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return a=Object.assign({},e.batchForm),t.next=3,n["a"].DETONATOR_BATCH(a);case 3:o=t.sent,o&&o>=0?(e.$message.success("批量操作成功"),e.dialogBatchFormVisible=!1):e.$message.error("批量操作失败");case 5:case"end":return t.stop()}}),t)})))()},getToday:function(){var e=+new Date;return e%864e5}}},m=c,u=(a("9d0a"),a("2877")),d=Object(u["a"])(m,o,l,!1,null,"f60b19d0",null);t["default"]=d.exports},"9bd2":function(e,t,a){"use strict";a("99af"),a("d3b7");var o=a("4360"),l=a("cebe"),r=a.n(l),i=a("5f72"),s=a("c276"),n=Object({NODE_ENV:"production",VUE_APP_TITLE:"民爆信息管理系统",VUE_APP_API:"/api/",VUE_APP_REPO:"https://github.com/d2-projects/d2-admin-start-kit",VUE_APP_I18N_LOCALE:"zh-chs",VUE_APP_I18N_FALLBACK_LOCALE:"en",VUE_APP_ELEMENT_COLOR:"#409EFF",VUE_APP_VERSION:"zhangjunyi",VUE_APP_BUILD_TIME:"2020-11-10 17:18:37",BASE_URL:"/"}).VUE_APP_CAS_URL;function c(e){var t=new Error(e);throw m(t),t}function m(e){o["a"].dispatch("d2admin/log/add",{type:"error",err:e,info:"数据请求异常"}),"timeout of 5000ms exceeded"===e.message&&(e.message="请求超时"),Object(i["Message"])({message:e.message,type:"error",duration:5e3})}var u=r.a.create({baseURL:"/api/",timeout:1e4});u.interceptors.request.use((function(e){if(!/^https:\/\/|http:\/\//.test(e.url)){var t=s["a"].cookies.get("token");t&&"undefined"!==t&&(e.headers["X-Token"]=t)}return/^\/open\//.test(e.url)&&(e.baseURL="/"),e}),(function(e){console.log(e),Promise.reject(e)})),u.interceptors.response.use((function(e){var t=e.data,a=t.code;if(void 0===a)return t;switch(a){case 0:return t.data;case"xxx":c("".concat(e.config.url,": [ code: xxx ] ").concat(t.msg));break;default:c("".concat(e.config.url,": ").concat(t.message));break}}),(function(e){if(e&&e.response)switch(e.response.status){case 400:e.message="请求错误";break;case 401:window.location.href=e.response.message||"".concat(n,"/login?service=").concat(encodeURIComponent(window.location.href)),e.message="未授权，请登录";break;case 403:e.message="权限不够，如需调整请联系架构组同学";break;case 404:e.message="请求地址出错: ".concat(e.response.config.url);break;case 408:e.message="请求超时";break;case 500:e.message="服务器内部错误";break;case 501:e.message="服务未实现";break;case 502:e.message="网关错误";break;case 503:e.message="服务不可用";break;case 504:e.message="网关超时";break;case 505:e.message="HTTP版本不受支持";break;default:break}return m(e),Promise.reject(e)})),t["a"]=u},"9d0a":function(e,t,a){"use strict";var o=a("9e20"),l=a.n(o);l.a},"9e20":function(e,t,a){}}]);