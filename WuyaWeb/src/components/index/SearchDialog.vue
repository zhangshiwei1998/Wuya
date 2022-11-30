<template>
  <el-dialog title="内容搜索" width="30%" :visible.sync="visible"
             :lock-scroll="false" @close="searchDialogClosed">
    <!--内容主体-->
    <el-autocomplete v-model="queryString" :fetch-suggestions="debounceQuery" placeholder="Search..."
                     class="m-search" popper-class="m-search-item"
                     @select="handleSelect">
      <i class="search icon el-input__icon" slot="suffix"></i>
      <template slot-scope="{ item }">
        <div class="title">{{ item.title }}</div>
        <span class="content">{{ item.content }}</span>
      </template>
    </el-autocomplete>

  </el-dialog>
</template>

<script>
import {getSearchBlogList} from "@/api/blog";

export default {
  name: "SearchDialog",
  props: {
    'searchDialogVisible':{
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      queryString: '',
      visible: this.searchDialogVisible,
      timer: null,
      queryResult: [],
    }
  },
  methods: {
    searchDialogClosed() {
      this.$emit("close", "visible");
    },
    debounceQuery(queryString, callback) {
      this.timer && clearTimeout(this.timer)
      this.timer = setTimeout(() => this.querySearchAsync(queryString, callback), 1000)
    },
    querySearchAsync(queryString, callback) {
      if (queryString == null
          || queryString.trim() === ''
          || queryString.indexOf('%') !== -1
          || queryString.indexOf('_') !== -1
          || queryString.indexOf('[') !== -1
          || queryString.indexOf('#') !== -1
          || queryString.indexOf('*') !== -1
          || queryString.trim().length > 20) {
        return
      }
      getSearchBlogList(queryString).then(res => {
        if (res.code === 200) {
          this.queryResult = res.data
          if (this.queryResult.length === 0) {
            this.queryResult.push({title: '无相关搜索结果'})
          }
          callback(this.queryResult)
        }
      }).catch(() => {
        this.msgError("请求失败")
      })
    },
    handleSelect(item) {
      if (item.id) {
        this.$router.push(`/blog/${item.id}`)
      }
    },
  }
}
</script>