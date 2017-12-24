package Chapter4

import java.util.Date

trait Visitor {
  def id: String      // Unique id assigned to each user
  def createdAt: Date // Date this user first visited the site

  // How long has this visitor been around
  def age: Long = new Date().getTime - createdAt.getTime
}
