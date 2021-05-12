<template>
  <div>
    <head-top></head-top>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input v-model="searchInfo"
                    clearable
                    placeholder="查询图书(根据名称、作者、出版社)">
            <el-button slot="append"
                       icon="el-icon-search"
                       @click="getStoreLogByInfo"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button @click="clearFilter">清除状态筛选</el-button>
        </el-col>
      </el-row>
      <el-table :data="storeLogList"
                ref="filterTable"
                border
                height="740"
                stripe
                style="width: 100%">
        <el-table-column prop="bookName"
                         label="书籍名称">
        </el-table-column>
        <el-table-column prop="bookAuthor"
                         label="书籍作者">
        </el-table-column>
        <el-table-column prop="bookPublish"
                         label="书籍出版社">
        </el-table-column>
        <el-table-column prop="bookISBN"
                         label="书籍ISBN"
                         sortable>
        </el-table-column>
        <el-table-column prop="putDate"
                         label="入库日期"
                         sortable>
          <template slot-scope="scope">{{scope.row.putDate | formatDate}}</template>
        </el-table-column>
        <el-table-column prop="putAdminId"
                         label="管理员编号"
                         width="100">
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>
<script>
import HeadTop from './ManageHeadTop'
import { formatDate } from '@/assets/js/date'
export default {
  filters: {
    formatDate (time) {
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  components: {
    HeadTop
  },
  created () {
    this.getStoreLog()
  },
  data () {
    return {
      storeLogList: [],
      searchInfo: ''
    }
  },
  methods: {
    async getStoreLog () {
      const { data: res } = await this.$http.get('storeLog?info=')
      if (res.message == 'success') {
        this.storeLogList = res.data.storeLogList
      } else {
        this.$message.error('服务器出错')
      }
    },
    async getStoreLogByInfo () {
      const { data: res } = await this.$http.get('storeLog?info=' +
        this.searchInfo)
      if (res.message == 'success') {
        this.storeLogList = res.data.storeLogList
      } else {
        this.$message.error('服务器出错')
      }
    },
    clearFilter () {
      this.$refs.filterTable.clearFilter()
    }
  }
}
</script>
<style lang="less">
</style>
