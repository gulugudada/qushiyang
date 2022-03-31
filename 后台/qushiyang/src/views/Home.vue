<template>
	<el-container class="home_container">
		<el-header>
			<div>
				<img src="../assets/logo.png" width="60" height="60">
				<span>趣食养后台管理系统</span>
			</div>
			<div>
				<el-button type="primary" >退出</el-button>
			</div>
		</el-header>
		<el-container>
			<!-- 左边菜单部分 -->
			<el-aside width="200px">
				<el-menu
				    default-active="2"
				    class="el-menu-vertical-demo"
					background-color="rgb(51,55,68)"
					text-color="white"
					active-text-color="yellow"
					unique-opened="true"
					:router="true">
				    <el-submenu index="1">
				        <template v-slot:title="title">
							<i class="el-icon-location"></i>
							<span>用户管理</span>
				        </template>
						<el-menu-item index="1-1">查看用户</el-menu-item>
					</el-submenu>
					<el-submenu index="2">
					    <template v-slot:title="title">
							<i class="el-icon-s-order"></i>
							<span>季节食谱管理</span>
					    </template>
						<el-menu-item index="2-1">查看食谱</el-menu-item>
					</el-submenu>
					<el-submenu index="3">
					    <template v-slot:title="title">
							<i class="el-icon-s-cooperation"></i>
							<span>体质食谱管理</span>
					    </template>
						<el-menu-item index="3-1">查看食谱</el-menu-item>
					</el-submenu>
					<el-submenu index="4">
					    <template v-slot:title="title">
							<i class="el-icon-s-shop"></i>
							<span>中药药材管理</span>
					    </template>
						<el-menu-item index="4-1">查看药材</el-menu-item>
					</el-submenu>
					<el-submenu index="5">
					    <template v-slot:title="title">
							<i class="el-icon-s-comment"></i>
							<span>机器人回答管理</span>
					    </template>
						<el-menu-item index="5-1">查看答疑</el-menu-item>
					</el-submenu>
					<el-submenu index="6">
					    <template v-slot:title="title">
							<i class="el-icon-s-opportunity"></i>
							<span>用户反馈管理</span>
					    </template>
						<el-menu-item index="6-1">查看用户反馈</el-menu-item>
					</el-submenu>
				</el-menu>
			</el-aside>
		    <el-main>
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
/* eslint-disable */
	export default {
		data(){
			return {
				
			}
		}
	}
</script>

<style scoped="scoped">
	.home_container{
		height: 100%;
	}
	.el-header{
		background: rgb(55,61,65);
		display: flex;
		justify-content: space-between;
		line-height: 60px;
		color: #F0F0F0;
		font-size: 22px;
	}
	.el-header img{
		
		vertical-align: middle;
	}
	.el-aside{
		background: rgb(51,55,68);
	}
	.el-main{
		background: rgb(234,237,241);
	}
</style>