package org.nymph

import java.sql.Time

class LastModifiedTime(rs: Map<String, Time>) {
    val value: Time by rs
}
