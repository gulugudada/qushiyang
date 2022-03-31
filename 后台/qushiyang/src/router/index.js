import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
	  path: '/home',
	  name: 'Home',
	  component: Home,
	  children:[
		  {
			  path:'/1-1',
			  name:'User',
			  component: () => import(/* webpackChunkName: "about" */ '../components/User.vue')
		  },
		  {
			  path:'/2-1',
			  name:'Season',
			  component: () => import(/* webpackChunkName: "about" */ '../components/Season.vue')
		  },
		  {
			  path:'/3-1',
			  name:'TiZhi',
			  component: () => import(/* webpackChunkName: "about" */ '../components/TiZhi.vue')
		  },
		  {
			  path:'/4-1',
			  name:'Drug',
			  component: () => import(/* webpackChunkName: "about" */ '../components/Drug.vue')
		  },
		  {
			  path:'/5-1',
			  name:'Enquire',
			  component: () => import(/* webpackChunkName: "about" */ '../components/Enquire.vue')
		  },
		  {
			  path:'/6-1',
		  	  name:'Fankui',
			  component: () => import(/* webpackChunkName: "about" */ '../components/Fankui.vue')
		  },
	  ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
