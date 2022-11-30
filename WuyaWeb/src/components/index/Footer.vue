<template>
	<footer class="ui inverted vertical segment m-padded-tb-large m-opacity">
		<div class="ui center aligned container">

			<p class="m-text-thin m-text-spaced m-opacity-tiny">
				<span style="margin-right: 10px" v-if="siteInfo.copyright">{{ siteInfo.copyright.title }}</span>
				<router-link to="/" style="color:#ffe500" v-if="siteInfo.copyright">{{ siteInfo.copyright.siteName }}</router-link>
				<span style="margin: 0 15px" v-if="siteInfo.copyright && siteInfo.beian">|</span>
				<a rel="external nofollow noopener" href="https://beian.miit.gov.cn/" target="_blank" style="color:#ffe500">{{ siteInfo.beian }}</a>
			</p>

			<div class="github-badge" v-for="(item,index) in badges" :key="index">
				<a rel="external nofollow noopener" :href="item.url" target="_blank" :title="item.title">
					<span class="badge-subject">{{ item.subject }}</span>
					<span class="badge-value" :class="`bg-${item.color}`">{{ item.value }}</span>
				</a>
			</div>

		</div>
	</footer>
</template>

<script>
	export default {
		name: "Footer",
		props: {
			siteInfo: {
				type: Object,
				required: true
			},
			badges: {
				type: Array,
				required: true
			},
			newBlogList: {
				type: Array,
				required: true
			}
		},
		methods: {
			toBlog(blog) {
				this.$store.dispatch('goBlogPage', blog)
			}
		}
	}
</script>

<style scoped>
	@import "../../assets/css/badge.css";

	.github-badge a {
		color: #fff;
	}
</style>