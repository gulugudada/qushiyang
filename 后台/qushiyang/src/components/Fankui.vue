<template>
	<el-card>
		<el-col>
			<el-input style="width: 400px;"
				placeholder="请输入用户名"
				v-model="input"
				clearable>
			</el-input>
			<el-button slot="append" icon="el-icon-search" @click="getSearch()"></el-button>
		</el-col>
		<el-col >
			<el-input style="width: 400px;margin-top: 0px;margin-right: 500px;"
				placeholder="请输入序号"
				v-model="input1"
				clearable>
			</el-input>
			<el-button style="margin-left: -500px;position: absolute;" type="primary" @click="dialogFormVisible = !dialogFormVisible">删除用户</el-button>
		</el-col>
		<el-dialog title="删除此条记录" :model-value="dialogFormVisible">
			<el-form :model="form">
			<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="DeleteAccount()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<!-- 渲染表格请求 -->
		<el-table
		    :data="fankuiList"
		    border
		    style="width: 100%">
		    <el-table-column
				type="index"
				width="50">
		    </el-table-column>
		    <el-table-column
				prop="username"
				label="用户名"
				width="120">
		    </el-table-column>
			<el-table-column
				prop="fankui"
				label="反馈">
		    </el-table-column>
			
			<!-- <el-table-column
				label="操作">
				<el-tooltip class="item" effect="dark" content="修改用户" placement="top">
				    <el-button type="primary" icon="el-icon-edit" circle></el-button>
				</el-tooltip>
				<el-tooltip class="item" effect="dark" content="删除用户" placement="top">
				    <el-button type="danger" icon="el-icon-delete" circle></el-button>
				</el-tooltip>
			</el-table-column> -->
		</el-table>
		<!-- 分页功能 -->
		<el-pagination
		  	background
		  	layout="prev, pager, next"
			page-size="5"
			:current-page="pagenum"
			@current-change="handleCurrentChange"
		  	:page-count="total">
		</el-pagination>
	</el-card>
</template>

<script>
	export default {
		data(){
			return {
				input:'',
				fankuiList:[],
				pagenum:1,
				total:0,
				all:true,
				search:false,
				dialogFormVisible:false
			}
		},
		methods: {
			getFankuiList(val){
				var that = this
				that.all = true
				that.search = false
				this.$http.post('http://dvwbxngn.dnat.tech/getAllFankui',{
					pagenum:val
				})
				.then(function(res){
					that.fankuiList = res.data.fankuiList,
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			startsearch(val){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/searchFankui',{
					pagenum:val,
					search:that.input
				})
				.then(function(res){
					that.fankuiList = res.data.fankuiList,
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			getSearch(){
				var that = this
				this.startsearch(1)
				that.pagenum = 1
				that.search = true
				that.all = false
			},
			DeleteAccount(){
				
			},
			//当前页码发生改变触发
			handleCurrentChange(val){
				var that = this
				if(that.all){
					this.getFankuiList(val)
				}
				else {
					this.startsearch(val)
				}
			}
		},
		created(){
			this.getFankuiList(1)
		}
	}
</script>

<style>
</style>
