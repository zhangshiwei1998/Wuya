<template>
	<div class="site">
    <div class="ui container">
      <div class="ui stackable grid">
        <!--左侧-->
        <div class="three wide column m-mobile-hide">
          <Nav :blogName="siteInfo.blogName" :categoryList="categoryList"/>
          <Tags :tagList="tagList" :class="{'m-display-none':focusMode}"/>
          <!--只在文章页面显示目录-->
          <Tocbot v-if="$route.name==='blog'"/>
        </div>
        <!--右侧-->
        <div class="ten wide column">
          <keep-alive include="Home">
            <router-view/>
          </keep-alive>
        </div>
      </div>
    </div>

		<!--私密文章密码对话框-->
		<BlogPasswordDialog/>

		<!--回到顶部-->
		<el-backtop style="box-shadow: none;background: none;z-index: 9999;">
			<img src="/img/paper-plane.png" style="width: 40px;height: 40px;">
		</el-backtop>
		<!--底部footer-->
		<Footer :siteInfo="siteInfo" :badges="badges"/>
	</div>
</template>

<script>
	import {getSite} from '@/api/index'
	import Nav from "@/components/index/Nav";
	import Footer from "@/components/index/Footer";
	import Tags from "@/components/sidebar/Tags";
	import Tocbot from "@/components/sidebar/Tocbot";
	import BlogPasswordDialog from "@/components/index/BlogPasswordDialog";
	import {mapState} from 'vuex'
	import {SAVE_CLIENT_SIZE, SAVE_INTRODUCTION, SAVE_SITE_INFO, RESTORE_COMMENT_FORM} from "@/store/mutations-types";

	export default {
		name: "Index",
		components: {BlogPasswordDialog, Tocbot, Tags, Nav, Footer},
		data() {
			return {
				siteInfo: {
					blogName: ''
				},
				categoryList: [],
				tagList: [],
				badges: [],
			}
		},
		computed: {
			...mapState(['focusMode'])
		},
		watch: {
			//路由改变时，页面滚动至顶部
			'$route.path'() {
				this.scrollToTop()
			}
		},
		created() {
			this.getSite()
			//从localStorage恢复之前的评论信息
			this.$store.commit(RESTORE_COMMENT_FORM)
		},
		mounted() {
			//保存可视窗口大小
			this.$store.commit(SAVE_CLIENT_SIZE, {clientHeight: document.body.clientHeight, clientWidth: document.body.clientWidth})
			window.onresize = () => {
				this.$store.commit(SAVE_CLIENT_SIZE, {clientHeight: document.body.clientHeight, clientWidth: document.body.clientWidth})
			}
		},
		methods: {
			getSite() {
				getSite().then(res => {
					if (res.code === 200) {
						this.siteInfo = res.data.siteInfo
						this.badges = res.data.badges
						this.categoryList = res.data.categoryList
						this.tagList = res.data.tagList
						this.$store.commit(SAVE_SITE_INFO, this.siteInfo)
						this.$store.commit(SAVE_INTRODUCTION, res.data.introduction)
						document.title = this.$route.meta.title + this.siteInfo.webTitleSuffix
					}
				})
			}
		}
	}
</script>

<style scoped>
	.site {
		display: flex;
		min-height: 100vh; /* 没有元素时，也把页面撑开至100% */
		flex-direction: column;
	}

	.ui.container {
		width: 1400px !important;
    flex: 1;
	}

	.m-display-none {
		display: none !important;
	}
	.ui.grid{
		justify-content: center;
	}
</style>