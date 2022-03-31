<template>
	<div class="login">
		<div class="login_context">
			<div class="login_title">
				<h3>趣食养后台管理登陆</h3>
			</div>
			<el-form :model="loginForm" :rules="loginFormRul" ref="loginFormRel" label-width="120px" class="login_box">
				<el-form-item prop="account">
					<el-input prefix-icon="el-icon-user" v-model="loginForm.account"></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input prefix-icon="el-icon-lock" type="password" v-model="loginForm.password"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click=login>登录</el-button>
					<el-button type="info">重置</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
/* eslint-disable */
	export default {
		data(){
			return {
				// 表单请求数据
				loginForm:{
					account:'123456',
					password:'123456'
				},
				// 表单对照规则
				loginFormRul:{
					
				}
			}
		},
		methods:{
			//登录
			login() {
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/SuperManagerLogin',this.loginForm)
				.then(function(res){
					if(res.data.code == -1){
						that.$message.error(res.data.msg);
					}
					else {
						that.$router.push('/home')
					}
				})
				.catch(function(err){
					console.log(err)
				})
			}
		}
	}
</script>

<style scoped="scoped">
	.login{
		width: 100%;
		height: 100%;
		background-size: 100% 100%;
		background-Repeat: no-repeat;
		bottom: 0;
		top: 0;
		left: 0;
		right: 0;
		position:absolute;
		background-attachment: fixed;
		background-image:url('../assets/background.png');
	}
	.login_context{
		width: 450px;
		height: 300px;
		background-size: 100% 100%;
		background-Repeat: no-repeat;
		background-attachment: fixed;
		background-image:url('../assets/background2.png');
		margin:auto;
		text-align: center;
		position: absolute;
		top:0;
		left:0;
		bottom:0;
		right:0;
	}
	.login_title{
		font-family:隶书;
		color: white;
		line-height: 48px;
		font-size: 26px;
		text-align:center;
		top: 0px;
	}
	.login_box{
		position: absolute;
		text-align: center;
		bottom: 0px;
		padding: 0;
	}
</style>
