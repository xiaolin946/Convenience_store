import {createRouter, createWebHistory} from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'index',
        component: ()=> import('../views/index.vue'),
        children: [
            {path: '/', name: "Tmain", component: ()=> import('../components/Tmain.vue')},
            {path: 'accessory', name: "accessory", component: ()=> import('../components/accessory.vue')},

        ]
    },
    {
        path: '/User',
        name: 'User',
        component: ()=> import('../views/User.vue'),
        children: [
            {path: 'main', name: "user_main", component: ()=> import('../components/Tmain.vue')},
            {path: 'accessory', name: "user_accessory", component: ()=> import('../components/accessory.vue')},
            {path: 'myxx', name: 'user_myxx', component: ()=> import('../components/myxx.vue')},
            {path: 'upsd', name: 'user_upsd', component: ()=> import('../components/upsd.vue')}
        ]
    },
    {
        path: '/Login',
        name: 'Login',
        component: ()=> import('../views/Login.vue'),
    },
    {
        path: '/Admin',
        name: 'Admin',
        component: ()=> import('../views/Admin.vue'),
        children: [
            {path: 'tip', name: "admin_tip", component: ()=> import('../components/Admin_tip.vue')},
            {path: 'header', name: "admin_header", component: ()=> import('../components/Admin_header.vue')},
            {path: 'aside', name: "admin_aside", component: ()=> import('../components/Admin_aside.vue')},
            {path: 'type', name: "admin_type", component: ()=> import('../components/Admin_type.vue')},
            {path: 'accessory', name: "admin_accessory", component: ()=> import('../components/Admin_accessory.vue')},
            {path: 'forder', name: "admin_Forder", component: ()=> import('../components/Admin_Forder.vue')},
            {path: 'torder', name: "admin_Torder", component: ()=> import('../components/Admin_Torder.vue')},
            {path: 'oorder', name: "admin_Oorder", component: ()=> import('../components/Admin_Oorder.vue')},
            {path: 'myxx', name: 'admin_myxx', component: ()=> import('../components/myxx.vue')},
            {path: 'upsd', name: 'admin_upsd', component: ()=> import('../components/upsd.vue')}
        ]
    },

]

const router = createRouter({
    history: createWebHistory("/"),
    routes: routes,
})



//输出对象
export default router