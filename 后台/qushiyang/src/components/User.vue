<template>
	<el-card>
		<el-col>
			<el-input style="width: 400px;"
				placeholder="请输入内容"
				v-model="input"
				clearable>
			</el-input>
			<el-button slot="append" icon="el-icon-search" @click="getSearch(index)"></el-button>
		</el-col>
		<el-col>
			<el-button style="margin-left: 50px;position: absolute;" type="primary" @click="dialogFormVisible = !dialogFormVisible">添加用户</el-button>
		</el-col>
		<el-col >
			<el-input style="width: 400px;margin-top: 0px;margin-right: 500px;"
				placeholder="请输入序号"
				v-model="input1"
				clearable>
			</el-input>
			<el-button style="margin-left: -394px;position: absolute;" type="primary" @click="updateAccount()">修改用户</el-button>
			<el-button style="margin-left: -290px;position: absolute;" type="primary" @click="dialogFormVisible2=!dialogFormVisible2">删除用户</el-button>
		</el-col>
		<!-- 渲染表格请求 -->
		<el-table
		    :data="userList"
		    border
		    style="width: 100%">
		    <el-table-column
				type="index"
				:index="indexMethod"
				width="180">
		    </el-table-column>
		    <el-table-column
				prop="account"
				label="手机号"
				width="180">
		    </el-table-column>
			<el-table-column
				prop="username"
				label="用户名">
		    </el-table-column>
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
	<el-dialog title="添加用户" :model-value="dialogFormVisible">
		<el-form :model="form">
		<el-form-item label="手机号码" :label-width="formLabelWidth">
	      <el-input v-model="form.account" autocomplete="off"></el-input>
	    </el-form-item>
		<el-form-item label="用户名" :label-width="formLabelWidth">
		  <el-input v-model="form.username" autocomplete="off"></el-input>
		</el-form-item>
		<el-form-item label="密码" :label-width="formLabelWidth">
		  <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
		</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="dialogFormVisible = false">取 消</el-button>
			<el-button type="primary" @click="addAccount()">确 定</el-button>
		</div>
	</el-dialog>
	<el-dialog title="修改用户" :model-value="dialogFormVisible1">
		<el-form :model="form1">
		<el-form-item label="用户名" :label-width="formLabelWidth">
		  <el-input v-model="form1.username" autocomplete="off"></el-input>
		</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="dialogFormVisible1 = false">取 消</el-button>
			<el-button type="primary" @click="sureUpdateAccount()">确 定</el-button>
		</div>
	</el-dialog>
	<el-dialog title="删除此用户" :model-value="dialogFormVisible2">
		<el-form :model="form1">
		<div slot="footer" class="dialog-footer">
				<el-button @click="dialogFormVisible2 = false">取 消</el-button>
				<el-button type="primary" @click="deleteAccount()">确 定</el-button>
		</div>
		</el-form>
	</el-dialog>
</template>

<script>
	export default {
		data(){
			return {
				input: '',
				input1:'',
				// 请求的参数
				pagenum: 1,
				userList:[],
				total:0,
				all:true,
				search:false,
				dialogFormVisible: false,
				dialogFormVisible1:false,
				dialogFormVisible2:false,
				form:{
					account:'',
					username:'',
					password:''
				},
				form1:{
					username:''
				}
			}
		},
		methods: {
			getUserList(val){
				var that = this
				that.all = true
				that.search = false
				this.$http.post('http://dvwbxngn.dnat.tech/getAllAccount',{
					pagenum:val
				})
				.then(function(res){
					that.userList = res.data.accountList,
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			startsearch(val){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/searchAccount',{
					pagenum:val,
					search:that.input
				})
				.then(function(res){
					that.userList = res.data.accountList,
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
			//当前页码发生改变触发
			handleCurrentChange(val){
				var that = this
				if(that.all){
					this.getUserList(val)
				}
				else {
					this.startsearch(val)
				}
			},
			addAccount(){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/addAccount2',this.form)
				.then(function(res){
					if(res.data.code == -1){
						that.$message.error(res.data.msg);
					}
					else {
						that.$message.success()(res.data.msg);
						that.dialogFormVisible = false
						this.getUserList(1)
					}
				})
				.catch(function(err){
					console.log(err)
				})
			},
			updateAccount(){
				var that = this
				that.form1.account = that.userList[parseInt(that.input1)-1].account
				that.form1.username = that.userList[parseInt(that.input1)-1].username
				that.dialogFormVisible1 = !that.dialogFormVisible1
			},
			sureUpdateAccount(){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/updateAccount1',{
					account:that.userList[that.input1-1].account,
					username:that.form1.username
				})
				.then(function(res){
					if(res.data.code == -1){
						that.$message.error(res.data.msg);
					}
					else {
						that.dialogFormVisible1 = !that.dialogFormVisible1
						this.getUserList(1)
					}
				})
				.catch(function(err){
					console.log(err)
				})
			},
			deleteAccount(){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/deleteAccount1',{
					account:that.userList[that.input1-1].account,
				})
				.then(function(res){
					if(res.data.code == -1){
						that.$message.error(res.data.msg);
					}
					else {
						that.dialogFormVisible2 = false
						this.getUserList(1)
					}
				})
				.catch(function(err){
					console.log(err)
				})
			}
		},
		created(){
			this.getUserList(1)
		}
	}
</script>

<style>
</style>
